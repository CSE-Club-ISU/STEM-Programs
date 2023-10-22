package src;

/**
 * A regular Queue that can resize when nessesary
 */
class MyQueue<T> {
    Object[] queue;
    int startIndex;
    int length;

    MyQueue() {
        queue = new Object[10];
        startIndex = 0;
        length = 0;
    }

    public boolean add(T t) {
        if (length == queue.length) resize();
        if (startIndex + length >= queue.length) {
            queue[startIndex + length - queue.length] = t;
        } else {
            queue[startIndex + length] = t;
        }
        length++;
        return true;
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
            int backLength = startIndex + length - oldArr.length;
            System.arraycopy(oldArr, startIndex, queue, 0, length - backLength);
            System.arraycopy(oldArr, 0, queue, length - backLength, backLength);
        } else {
            System.arraycopy(oldArr, startIndex, queue, 0, length);
        }
        startIndex = 0;
    }
}