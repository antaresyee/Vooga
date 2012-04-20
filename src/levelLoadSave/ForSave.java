package levelLoadSave;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)

/**
 * @author antaresyee
 * Mark each GameObject class you want saved with this annotation.  See Barrier class for an example.
 */

public @interface ForSave {

}
