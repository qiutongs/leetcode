---
title: Rate Limiter
tags: Mutithread
---

## 问题
UBER的面试题

## 总体思路
- Token Bucket

## 方案

- 精确到second
- acquire不是完全准确: get和decrement之间不是atomic
- 引入了一个daemon thread来给bucket加token

{% highlight java %}
public class RateLimiter {

	private final AtomicInteger token;

	public RateLimiter(int rate, int burst) {
		// ignore validation

		// initialize token with burst
		this.token = new AtomicInteger(burst);

		// create a daemon thread to fill the token
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					// token to add is either rate or up to max
					int delta = Math.min(burst - token.get(), rate);

					// add token
					token.getAndAdd(delta);

					// sleep 1 second
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						break;
					}
				}
			}

		}).start();
	}

	public boolean acquire() {
		if (token.get() > 0) {
			token.decrementAndGet();
			return true;
		} else {
			return false;
		}
	}

	public static class RequestRunnable implements Runnable {

		private final int id;
		private final int sleepTime;
		private final long startTime;
		private final RateLimiter rateLimiter;

		public RequestRunnable(int id, RateLimiter rateLimiter) {
			this.id = id;
			this.sleepTime = Math.abs(new Random().nextInt() % 50);
			this.startTime = System.currentTimeMillis();
			this.rateLimiter = rateLimiter;
		}

		@Override
		public void run() {
			try {
				Thread.sleep(sleepTime*1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			System.out.println(id
					+ ":"
					+ (System.currentTimeMillis() - startTime) / 1000
					+ ":"
					+ rateLimiter.acquire());
		}

	}

	public static void main(String[] args) {

		RateLimiter rateLimiter = new RateLimiter(1, 1);

		for (int i = 0; i < 50; i++) {
			new Thread(new RequestRunnable(i, rateLimiter)).start();
		}
	}

}
{% endhighlight %}
