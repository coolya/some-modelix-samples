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
 * Generated for http://127.0.0.1:63320/node?ref=r%3A00000000-0000-4000-0000-011c89590288%28jetbrains.mps.lang.core.structure%29%2F5259630923505770665
 */
public class TypeAnnotated extends BaseConcept {

  public class Properties extends BaseConcept.Properties implements INodeHolder {

    @NotNull
    @Override
    public INode getINode() {
      return TypeAnnotated.this.getINode();
    }
  }
  public class Children extends BaseConcept.Children implements INodeHolder {

    @NotNull
    @Override
    public INode getINode() {
      return TypeAnnotated.this.getINode();
    }
    public <T extends BaseConcept> T createAnnotation(AbstractConcept<T> concept) {
      INode newChild;
      if (getINode().getChildren("annotation").iterator().hasNext()) {
        throw new IllegalStateException("child already created!");
      } else {
        newChild = getINode().addNewChild("annotation", 0, concept);
      }

      return MPSLanguageRegistry.Companion.getInstance(newChild);
    }
    @Nullable
    public BaseConcept getAnnotation() {
      Iterator<INode> iterator = getINode().getChildren("annotation").iterator();
      if (iterator.hasNext()) {
        INode child = iterator.next();
        return MPSLanguageRegistry.Companion.getInstance(child);
      }
      return null;
    }
    @Nullable
    public BaseConcept setAnnotation(@Nullable BaseConcept value) {
      INode storageNode = getINode();
      Iterator<INode> iterator = storageNode.getChildren("annotation").iterator();
      if (iterator.hasNext()) {
        INode current = iterator.next();

        if (value != null && Objects.equals(current, value.getINode())) {
          return value;
        }
        storageNode.removeChild(current);
      }

      if (value != null) {
        storageNode.moveChild("annotation", 0, value.getINode());
      }
      return value;
    }
  }
  public class References extends BaseConcept.References implements INodeHolder {

    @NotNull
    @Override
    public INode getINode() {
      return TypeAnnotated.this.getINode();
    }


  }

  private final Properties properties;
  private final Children children;
  private final References references;
  public TypeAnnotated(INode node) {
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
