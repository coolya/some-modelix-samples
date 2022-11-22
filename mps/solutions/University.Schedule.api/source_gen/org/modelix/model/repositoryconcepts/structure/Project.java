package org.modelix.model.repositoryconcepts.structure;

/*Generated by MPS */

import jetbrains.mps.lang.core.structure.BaseConcept;
import jetbrains.mps.lang.core.structure.INamedConcept;
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
 * Generated for http://127.0.0.1:63320/node?ref=r%3Af2f39a18-fd23-4090-b7f2-ba8da340eec2%28org.modelix.model.repositoryconcepts.structure%29%2F4008363636171860313
 */
public class Project extends BaseConcept implements INamedConcept {

  public class Properties extends BaseConcept.Properties implements INodeHolder, INamedConcept.Properties {

    @NotNull
    @Override
    public INode getINode() {
      return Project.this.getINode();
    }
  }
  public class Children extends BaseConcept.Children implements INodeHolder, INamedConcept.Children {

    @NotNull
    @Override
    public INode getINode() {
      return Project.this.getINode();
    }
    public <T extends Module> T createModules(AbstractConcept<T> concept) {
      INode newChild;
      newChild = getINode().addNewChild("modules", ((int) StreamSupport.stream(getINode().getChildren("modules").spliterator(), false).count()), concept);

      return MPSLanguageRegistry.INSTANCE.getInstance(newChild);
    }
    public <T extends ProjectModule> T createProjectModules(AbstractConcept<T> concept) {
      INode newChild;
      newChild = getINode().addNewChild("projectModules", ((int) StreamSupport.stream(getINode().getChildren("projectModules").spliterator(), false).count()), concept);

      return MPSLanguageRegistry.INSTANCE.getInstance(newChild);
    }
    @NotNull
    public List<? extends Module> getModules() {
      Iterable<INode> children = getINode().getChildren("modules");
      Stream<INode> stream = StreamSupport.stream(children.spliterator(), false);
      return stream.<Module>map(new Function<INode, Module>() {
        @Override
        public Module apply(INode node) {
          return MPSLanguageRegistry.INSTANCE.getInstance(node);
        }
      }).collect(Collectors.toList());
    }
    @NotNull
    public List<? extends ProjectModule> getProjectModules() {
      Iterable<INode> children = getINode().getChildren("projectModules");
      Stream<INode> stream = StreamSupport.stream(children.spliterator(), false);
      return stream.<ProjectModule>map(new Function<INode, ProjectModule>() {
        @Override
        public ProjectModule apply(INode node) {
          return MPSLanguageRegistry.INSTANCE.getInstance(node);
        }
      }).collect(Collectors.toList());
    }
  }
  public class References extends BaseConcept.References implements INodeHolder, INamedConcept.References {

    @NotNull
    @Override
    public INode getINode() {
      return Project.this.getINode();
    }


  }

  private final Properties properties;
  private final Children children;
  private final References references;
  public Project(INode node) {
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