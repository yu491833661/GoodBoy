package yxinfo.core.framework.util.transform;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dy on 2017/6/28.
 */
public class ListTransformer<T, E> {

    public List<E> listTransform( List<T> tList, Transformer<T, E> transformer ) {
        if ( tList == null || tList.size() == 0 ) {
            return null;
        }
        List<E> eList = new ArrayList<E>();
        for ( T t : tList ) {
            eList.add( transformer.transform( t ) );
        }
        return eList;
    }
}
