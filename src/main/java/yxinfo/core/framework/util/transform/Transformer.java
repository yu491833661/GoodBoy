package yxinfo.core.framework.util.transform;

/**
 * Created by dy on 2017/6/28.
 */
public interface Transformer<T, E> {

    E transform( T t );
}
