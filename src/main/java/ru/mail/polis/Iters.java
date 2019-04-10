package ru.mail.polis;

import com.google.common.base.Functions;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Function;

/**
 * Utility methods for iterators
 *
 * @author Dmitry Schitinin <dmitry.schitinin@corp.mail.ru>
 */
public final class Iters {

    private static final Iterator<Object> EMPTY = new Iterator<>() {
        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public Object next() {
            throw new NoSuchElementException("Next on empty iterator");
        }
    };

    private Iters() {

    }

    @SuppressWarnings("unchecked")
    public static <E> Iterator<E> empty() {
        return (Iterator<E>) EMPTY;
    }

    public static <E extends Comparable<E>> Iterator<E> until(@NotNull Iterator<E> iter, @NotNull E until) {
        return new UntilIterator<>(iter, until);
    }

    private static class UntilIterator<E extends Comparable<E>> implements Iterator<E> {
        private final Iterator<E> iter;
        private final E until;

        private E next;

        UntilIterator(@NotNull Iterator<E> iter, @NotNull E until) {
            this.iter = iter;
            this.until = until;
            this.next = iter.hasNext() ? iter.next() : null;
        }

        @Override
        public boolean hasNext() {
            return next != null && next.compareTo(until) < 0;
        }

        @Override
        public E next() {
            assert hasNext();

            final E result = this.next;
            this.next = iter.hasNext() ? iter.next() : null;
            return result;
        }
    }

    public static <E> Iterator<E> collapseEquals(@NotNull Iterator<E> iter,
                                                 @NotNull Function<E, ?> byKey) {
        return new CollapseEqualsIterator<>(iter, byKey);
    }

    public static <E> Iterator<E> collapseEquals(@NotNull Iterator<E> iter) {
        return new CollapseEqualsIterator<>(iter);
    }

    private static class CollapseEqualsIterator<E> implements Iterator<E> {

        private final Iterator<E> iter;
        private final Function<E, ?> keyExtractor;

        private E next;

        CollapseEqualsIterator(@NotNull Iterator<E> iter,
                               @NotNull Function<E, ?> keyExtractor) {
            this.iter = iter;
            this.keyExtractor = keyExtractor;
            this.next = iter.hasNext() ? iter.next() : null;
        }

        CollapseEqualsIterator(@NotNull Iterator<E> iter) {
            this(iter, Functions.identity());
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public E next() {
            assert hasNext();

            final E result = next;

            // Advance to the next distinct key
            this.next = null;
            while (iter.hasNext()) {
                final E next = iter.next();
                if (!keyExtractor.apply(next)
                        .equals(keyExtractor.apply(result))) {
                    this.next = next;
                    break;
                }
            }

            return result;
        }
    }

}
