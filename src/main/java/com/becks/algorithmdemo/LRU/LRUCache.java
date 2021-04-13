package com.becks.algorithmdemo.LRU;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ClassName LRUcache
 * @Description 使用LinkedHashMap实现LRU算法，采用的数据结构：hashmap + 链表
 * @Author Becks Hwang
 * @Date 2021/4/13 15:52
 */
public class LRUCache extends LinkedHashMap<Integer, Integer> {

    //链表容量
    private final int capacity;

    public LRUCache(int capacity){
        //第三个参数必须是true，表示按照访问顺序控制LinkedHashMap元素顺序，默认是false按照插入顺序排序
        //0.75f 默认的负载因子 (0.75) 在时间和空间成本之间提供了很好的权衡。更高的值减少了空间开销，但增加了查找成本，更低的值引起空间浪费。
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    public int get(Integer key){
        return super.getOrDefault(key, -1);
    }

    //重写判断是否可以删除元素，当大于链表容量时可以删除元素
    @Override
    public boolean removeEldestEntry(Map.Entry<Integer, Integer> oldLRUCaches){
        return size() > capacity;
    }

    public void systemOut(){
        System.out.println("key value");
        this.forEach((key,value) -> {
            System.out.println(key + " -> " + value);
        });
        System.out.println("==========================");
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(5);
        lruCache.put(1,1);
        lruCache.put(2,2);
        lruCache.put(3,3);
        lruCache.put(4,4);
        lruCache.put(5,5);
        System.out.println("初始化完毕：");
        lruCache.systemOut();

        lruCache.get(2);
        System.out.println("获取key=2以后的数据：");
        lruCache.systemOut();

        lruCache.get(1);
        System.out.println("获取key=1以后的数据：");
        lruCache.systemOut();

        lruCache.put(6,6);
        System.out.println("新增key=6以后的数据：");
        lruCache.systemOut();

        lruCache.put(3,3);
        System.out.println("新增key=3以后的数据：");
        lruCache.systemOut();
    }


}
