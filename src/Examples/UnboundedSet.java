package Examples;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

/*
Prompt

Implement an unbounded set where each entry has an expiration date and disappears once it has expired.

Clarify what an unbounded set is with examples of expiration date

"I wrote the code and please do a code review for me and provide feedback."

Solution

Expected Solution - Milestones

Clarifies the requirements including on expiration date.
Come up with a basic approach of what the solution.
Demonstrates of understanding of concepts like LRU cache, Hashmap and Priority queue.
Implements the expiration date from the beginning and determines the key expiration.
Handles expired keys and purges them.
*/
class UnboundedSet_TTLCache {
    private final int ttl;
    private final Map<String, Long> map; // (key, timestamp_added)
    private final PriorityQueue<Pair<Long, String>> heap; // min heap with timestamp entries
    private final Set<String> lazyDelete; // to lazily delete heap entries
    private final ReentrantLock lock; // reentrant lock to prevent deadlock scenario

    public UnboundedSet_TTLCache(int ttl) {
        this.ttl = ttl;
        this.map = new HashMap<>();
        this.heap = new PriorityQueue<>((a, b) -> a.getKey().compareTo(b.getKey())); // Min heap based on timestamp
        this.lazyDelete = new HashSet<>();
        this.lock = new ReentrantLock();
    }

    private void cleanup(long now) {
        lock.lock();
        try {
            while (!heap.isEmpty() && (heap.peek().getKey() + ttl < now || lazyDelete.contains(heap.peek().getValue()))) {
                Pair<Long, String> entry = heap.poll();
                if (entry != null && map.containsKey(entry.getValue())) {
                    map.remove(entry.getValue());
                }
            }
        } finally {
            lock.unlock();
        }
    }

    public void add(String key) {
        lock.lock();
        try {
            long now = System.currentTimeMillis() / 1000L;
            cleanup(now);
            map.put(key, now);
            heap.offer(new Pair<>(now, key));
        } finally {
            lock.unlock();
        }
    }

    public boolean contains(String key) {
        lock.lock();
        try {
            long now = System.currentTimeMillis() / 1000L;
            cleanup(now);
            return map.containsKey(key);
        } finally {
            lock.unlock();
        }
    }

    public void remove(String key) {
        lock.lock();
        try {
            if (!contains(key)) {
                throw new IllegalArgumentException(key + " doesn't exist");
            }
            lazyDelete.add(key);
            long now = System.currentTimeMillis() / 1000L;
            cleanup(now);
            map.remove(key);
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        UnboundedSet_TTLCache unboundedSetTtlCache = new UnboundedSet_TTLCache(10);
        unboundedSetTtlCache.add("A");
        Thread.sleep(20000);
        System.out.println(unboundedSetTtlCache.contains("A"));
    }
}

class Pair<K, V> {
    private final K key;
    private final V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}

