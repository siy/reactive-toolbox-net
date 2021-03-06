package org.reactivetoolbox.codec.json;

import org.reactivetoolbox.core.lang.Collection;
import org.reactivetoolbox.core.lang.Functions.FN0;

public interface CollectionDeserializer<T extends Collection> extends FN0<CollectionDeserializer.Collector<T>> {
    interface Collector<T> {
        Collector<T> add(Object element);
        T done();
    }
}
