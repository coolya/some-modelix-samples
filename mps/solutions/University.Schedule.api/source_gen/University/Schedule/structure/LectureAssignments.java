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
 * Generated for http://127.0.0.1:63320/node?ref=r%3Adfa26643-4653-44bc-9dfe-5a6581bcd381%28University.Schedule.structure%29%2F1648392019017048460
 */
public class LectureAssignments extends BaseConcept {

  public class Properties extends BaseConcept.Properties implements INodeHolder {

    @NotNull
    @Override
    public INode getINode() {
      return LectureAssignments.this.getINode();
    }
  }
  public class Children extends BaseConcept.Children implements INodeHolder {

    @NotNull
    @Override
    public INode getINode() {
      return LectureAssignments.this.getINode();
    }
    public <T extends Assignment> T createLectures(AbstractConcept<T> concept) {
      INode newChild;
      newChild = getINode().addNewChild("lectures", ((int) StreamSupport.stream(getINode().getChildren("lectures").spliterator(), false).count()), concept);

      return MPSLanguageRegistry.Companion.getInstance(newChild);
    }
    @NotNull
    public List<? extends Assignment> getLectures() {
      Iterable<INode> children = getINode().getChildren("lectures");
      Stream<INode> stream = StreamSupport.stream(children.spliterator(), false);
      return stream.<Assignment>map(new Function<INode, Assignment>() {
        @Override
        public Assignment apply(INode node) {
          return MPSLanguageRegistry.Companion.getInstance(node);
        }
      }).collect(Collectors.toList());
    }
  }
  public class References extends BaseConcept.References implements INodeHolder {

    @NotNull
    @Override
    public INode getINode() {
      return LectureAssignments.this.getINode();
    }
    @NotNull
    public Student getStudent() {
      return MPSLanguageRegistry.Companion.getInstance(getINode().getReferenceTarget("student"));
    }
    @NotNull
    public Student setStudent(@NotNull Student value) {
      getINode().setReferenceTarget("student", value.getINode());
      return value;
    }


  }

  private final Properties properties;
  private final Children children;
  private final References references;
  public LectureAssignments(INode node) {
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
