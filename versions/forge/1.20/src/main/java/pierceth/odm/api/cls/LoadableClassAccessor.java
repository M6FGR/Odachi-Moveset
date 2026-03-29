package pierceth.odm.api.cls;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

class LoadableClassAccessor {
    public static final Logger LOGGER = LogManager.getLogger("ILoadableClass");
    public static final List<Class<? extends ILoadableClass>> LOADED_CLASSES = new ArrayList<>();
}
