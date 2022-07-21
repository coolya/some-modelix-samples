package jetbrains.mps.lang.core.structure;

/*Generated by MPS */

import org.modelix.mps.apigen.runtime.INodeHolder;
import org.jetbrains.annotations.NotNull;
import org.modelix.model.api.INode;
import org.jetbrains.annotations.Nullable;

/**
 * Generated for http://127.0.0.1:63320/node?ref=r%3A00000000-0000-4000-0000-011c89590288%28jetbrains.mps.lang.core.structure%29%2F3364660638048049750
 */
public class PropertyAttribute extends Attribute {

  public class Properties extends Attribute.Properties implements INodeHolder {

    @NotNull
    @Override
    public INode getINode() {
      return PropertyAttribute.this.getINode();
    }
    @Nullable
    public String getName_DebugInfo() {
      String propertyValue = getINode().getPropertyValue("name_DebugInfo");
      return propertyValue;
    }
    @Nullable
    public String setName_DebugInfo(@Nullable String value) {
      getINode().setPropertyValue("name_DebugInfo", value);
      return value;
    }
    @Nullable
    public String getPropertyId() {
      String propertyValue = getINode().getPropertyValue("propertyId");
      return propertyValue;
    }
    @Nullable
    public String setPropertyId(@Nullable String value) {
      getINode().setPropertyValue("propertyId", value);
      return value;
    }
    @Nullable
    public Boolean getEnumUsageMigrated() {
      String propertyValue = getINode().getPropertyValue("enumUsageMigrated");
      if (propertyValue != null && !(propertyValue.isEmpty())) {
        return Boolean.parseBoolean(propertyValue);
      }
      return null;
    }
    @Nullable
    public Boolean setEnumUsageMigrated(@Nullable Boolean value) {
      if (value != null) {
        getINode().setPropertyValue("enumUsageMigrated", Boolean.toString(value));
      } else {
        getINode().setPropertyValue("enumUsageMigrated", null);
      }
      return value;
    }
  }
  public class Children extends Attribute.Children implements INodeHolder {

    @NotNull
    @Override
    public INode getINode() {
      return PropertyAttribute.this.getINode();
    }
  }
  public class References extends Attribute.References implements INodeHolder {

    @NotNull
    @Override
    public INode getINode() {
      return PropertyAttribute.this.getINode();
    }


  }

  private final Properties properties;
  private final Children children;
  private final References references;
  public PropertyAttribute(INode node) {
    super(node);
    this.properties = new Properties();
    this.children = new Children();
    this.references = new References();
  }
  public Properties getProperties() {
    return this.properties;
  }
  public Children getChildren() {
    return this.children;
  }
  public References getReferences() {
    return this.references;
  }
}
