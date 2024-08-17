package Utils;

/**
 * A regular Queue that can resize when necessary.
 * Note that it can loop around the array to better support additions and deletions.
 */
public class MyQueue<T> {
    Object[] queue;
    int startIndex;
    int length;

    public MyQueue() {
        queue = new Object[10];
        startIndex = 0;
        length = 0;
    }

    /**
     * Places the new element at the back of the queue
     */
    public void addBack(T t) {
        if (length == queue.length) resize();
        if (startIndex + length >= queue.length) {
            queue[startIndex + length - queue.length] = t;
        } else {
            queue[startIndex + length] = t;
        }
        length++;
    }

    /**
     * Places the new element at the front of the queue
     */
    public void addFront(T t) {
        if (length == queue.length) resize();
        if (startIndex == 0) {
            queue[queue.length - 1] = t;
            startIndex = queue.length - 1;
        } else {
            startIndex--;
            queue[startIndex] = t;
        }
        length++;
    }

    public T remove() {
        T toReturn = (T) queue[startIndex];
        if (startIndex == queue.length - 1) startIndex = 0;
        else startIndex++;
        length--;
        return toReturn;
    }

    public T peek() {
        return (T) queue[startIndex];
    }

    public boolean hasNext() {
        return length != 0;
    }

    private void resize() {
        Object[] oldArr = queue;
        queue = new Object[oldArr.length * 2];

        if (startIndex + length >= oldArr.length) {
            // We have a loop, so we need to copy both sections in order
            int backLength = startIndex + length - oldArr.length;
            System.arraycopy(oldArr, startIndex, queue, 0, length - backLength);
            System.arraycopy(oldArr, 0, queue, length - backLength, backLength);
        } else {
            System.arraycopy(oldArr, startIndex, queue, 0, length);
        }
        startIndex = 0;
    }

    public int size() {
        return length;
    }

    public boolean isEmpty() {
        return length == 0;
    }
}