---
title: Load Balancer
tags: Hash, Array
---

## 问题
[leetcode link](https://www.lintcode.com/problem/load-balancer/my-submissions)

## 总体思路
- 所有操作最好都O(1)
- 随机pick -> Array
- add/remove -> hashmap(server id : index)
  - remove有点tricky，如何处理数组？？？
  - 想到可以让数组的尾部来放删掉的data，track一个actual size

## 细节
- random
- remove时：swap删掉的数后，别忘记更新hashmap

## 方案

{% highlight java %}
public class LoadBalancer {

    private HashMap<Integer, Integer> serverIndexMap = new HashMap<>();
    private ArrayList<Integer> serverArray = new ArrayList<>();
    private int size = 0;

    public LoadBalancer() {

    }

    /*
     * @param server_id: add a new server to the cluster
     * @return: nothing
     */
    public void add(int server_id) {
        int index;
        // actual size is less than the array size
        if (size < serverArray.size()) {
            serverArray.set(size, server_id);
            index = size;
        } else {
            serverArray.add(server_id);
            index = serverArray.size()-1;
        }
        // either way, increment size
        size++;

        serverIndexMap.put(server_id, index);
    }

    /*
     * @param server_id: server_id remove a bad server from the cluster
     * @return: nothing
     */
    public void remove(int server_id) {
        Integer index = serverIndexMap.get(server_id);

        if (index != null) {
            serverIndexMap.remove(server_id);

            Integer toSwapId = serverArray.get(size-1);

            //swap the last element in array to this deleted one
            serverArray.set(index, serverArray.get(size-1));
            serverIndexMap.put(toSwapId, index);
            size--;
        }
    }

    /*
     * @return: pick a server in the cluster randomly with equal probability
     */
    public int pick() {
        return serverArray.get((new Random().nextInt() % size + size) % size);
    }
}
{% endhighlight %}
