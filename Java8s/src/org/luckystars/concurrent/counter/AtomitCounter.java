package org.luckystars.concurrent.counter;

import java.util.concurrent.atomic.AtomicLong;

public class AtomitCounter {

    private final AtomicLong count = new AtomicLong(0L);

    public Long countPlus(){
        return count.incrementAndGet();
    }


}
