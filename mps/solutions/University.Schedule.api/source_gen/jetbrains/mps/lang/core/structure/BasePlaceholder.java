package jetbrains.mps.lang.core.structure;

/*Generated by MPS */

import org.modelix.mps.apigen.runtime.INodeHolder;
import org.jetbrains.annotations.NotNull;
import org.modelix.model.api.INode;
import org.modelix.mps.apigen.runtime.AbstractConcept;
import org.modelix.mps.apigen.runtime.MPSLanguageRegistry;
import org.jetbrains.annotations.Nullable;
import java.util.Iterator;
import java.util.Objects;

/**
 * Generated for http://127.0.0.1:63320/node?ref=r%3A00000000-0000-4000-0000-011c89590288%28jetbrains.mps.lang.core.structure%29%2F3717301156197626279
 */
public class BasePlaceholder extends ChildAttribute {

  public class Properties extends ChildAttribute.Properties implements INodeHolder {

    @NotNull
    @Override
    public INode getINode() {
      return BasePlaceholder.this.getINode();
    }
  }
  public class Children extends ChildAttribute.Children implements INodeHolder {

    @NotNull
    @Override
    public INode getINode() {
      return BasePlaceholder.this.getINode();
    }
    public <T extends IPlaceholderContent> T createContent(AbstractConcept<T> concept) {
      INode newChild;
      if (getINode().getChildren("content").iterator().hasNext()) {
        throw new IllegalStateException("child already created!");
      } else {
        newChild = getINode().addNewChild("content", 0, concept);
      }

      return MPSLanguageRegistry.Companion.getInstance(newChild);
    }
    @Nullable
    public IPlaceholderContent getContent() {
      Iterator<INode> iterator = getINode().getChildren("content").iterator();
      if (iterator.hasNext()) {
        INode child = iterator.next();
        return MPSLanguageRegistry.Companion.getInstance(child);
      }
      return null;
    }
    @Nullable
    public IPlaceholderContent setContent(@Nullable IPlaceholderContent value) {
      INode storageNode = getINode();
      Iterator<INode> iterator = storageNode.getChildren("content").iterator();
      if (iterator.hasNext()) {
        INode current = iterator.next();

        if (value != null && Objects.equals(current, value.getINode())) {
          return value;
        }
        storageNode.removeChild(current);
      }

      if (value != null) {
        storageNode.moveChild("content", 0, value.getINode());
      }
      return value;
    }
  }
  public class References extends ChildAttribute.References implements INodeHolder {

    @NotNull
    @Override
    public INode getINode() {
      return BasePlaceholder.this.getINode();
    }


  }

  private final Properties properties;
  private final Children children;
  private final References references;
  public BasePlaceholder(INode node) {
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
