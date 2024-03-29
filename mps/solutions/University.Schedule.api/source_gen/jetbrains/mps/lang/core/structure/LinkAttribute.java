package jetbrains.mps.lang.core.structure;

/*Generated by MPS */

import org.modelix.mps.apigen.runtime.INodeHolder;
import org.jetbrains.annotations.NotNull;
import org.modelix.model.api.INode;
import org.jetbrains.annotations.Nullable;

/**
 * Generated for http://127.0.0.1:63320/node?ref=r%3A00000000-0000-4000-0000-011c89590288%28jetbrains.mps.lang.core.structure%29%2F3364660638048049745
 */
public class LinkAttribute extends Attribute {

  public class Properties extends Attribute.Properties implements INodeHolder {

    @NotNull
    @Override
    public INode getINode() {
      return LinkAttribute.this.getINode();
    }
    @Nullable
    public String getRole_DebugInfo() {
      String propertyValue = getINode().getPropertyValue("role_DebugInfo");
      return propertyValue;
    }
    @Nullable
    public String setRole_DebugInfo(@Nullable String value) {
      getINode().setPropertyValue("role_DebugInfo", value);
      return value;
    }
    @Nullable
    public String getLinkId() {
      String propertyValue = getINode().getPropertyValue("linkId");
      return propertyValue;
    }
    @Nullable
    public String setLinkId(@Nullable String value) {
      getINode().setPropertyValue("linkId", value);
      return value;
    }
  }
  public class Children extends Attribute.Children implements INodeHolder {

    @NotNull
    @Override
    public INode getINode() {
      return LinkAttribute.this.getINode();
    }
  }
  public class References extends Attribute.References implements INodeHolder {

    @NotNull
    @Override
    public INode getINode() {
      return LinkAttribute.this.getINode();
    }


  }

  private final Properties properties;
  private final Children children;
  private final References references;
  public LinkAttribute(INode node) {
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
