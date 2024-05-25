package jetbrains.mps.lang.core.structure;

/*Generated by MPS */

import org.modelix.mps.apigen.runtime.INodeHolder;
import org.jetbrains.annotations.Nullable;

public interface INamedConcept extends INodeHolder {
  interface Properties extends INodeHolder {
    @Nullable
    default String getName() {
      String propertyValue = getINode().getPropertyValue("name");
      return propertyValue;
    }
    @Nullable
    default String setName(@Nullable String value) {
      getINode().setPropertyValue("name", value);
      return value;
    }
  }
  interface Children extends INodeHolder {


  }
  interface References extends INodeHolder {

  }
  Properties getProperties();
  Children getChildren();
}