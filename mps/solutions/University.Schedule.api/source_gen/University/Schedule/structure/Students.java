package University.Schedule.structure;

/*Generated by MPS */

import jetbrains.mps.lang.core.structure.BaseConcept;
import org.modelix.mps.apigen.runtime.INodeHolder;
import org.jetbrains.annotations.NotNull;
import org.modelix.model.api.INode;
import org.modelix.mps.apigen.runtime.AbstractConcept;
import java.util.stream.StreamSupport;
import org.modelix.mps.apigen.runtime.MPSLanguageRegistry;
import java.util.List;
import java.util.stream.Stream;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Generated for http://127.0.0.1:63320/node?ref=r%3Adfa26643-4653-44bc-9dfe-5a6581bcd381%28University.Schedule.structure%29%2F1648392019017048457
 */
public class Students extends BaseConcept {

  public class Properties extends BaseConcept.Properties implements INodeHolder {

    @NotNull
    @Override
    public INode getINode() {
      return Students.this.getINode();
    }
  }
  public class Children extends BaseConcept.Children implements INodeHolder {

    @NotNull
    @Override
    public INode getINode() {
      return Students.this.getINode();
    }
    public <T extends Student> T createStudents(AbstractConcept<T> concept) {
      INode newChild;
      newChild = getINode().addNewChild("students", ((int) StreamSupport.stream(getINode().getChildren("students").spliterator(), false).count()), concept);

      return MPSLanguageRegistry.INSTANCE.getInstance(newChild);
    }
    @NotNull
    public List<? extends Student> getStudents() {
      Iterable<INode> children = getINode().getChildren("students");
      Stream<INode> stream = StreamSupport.stream(children.spliterator(), false);
      return stream.<Student>map(new Function<INode, Student>() {
        @Override
        public Student apply(INode node) {
          return MPSLanguageRegistry.INSTANCE.getInstance(node);
        }
      }).collect(Collectors.toList());
    }
  }
  public class References extends BaseConcept.References implements INodeHolder {

    @NotNull
    @Override
    public INode getINode() {
      return Students.this.getINode();
    }


  }

  private final Properties properties;
  private final Children children;
  private final References references;
  public Students(INode node) {
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
