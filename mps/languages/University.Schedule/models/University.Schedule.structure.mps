<?xml version="1.0" encoding="UTF-8"?>
<model ref="r:dfa26643-4653-44bc-9dfe-5a6581bcd381(University.Schedule.structure)">
  <persistence version="9" />
  <languages>
    <use id="c72da2b9-7cce-4447-8389-f407dc1158b7" name="jetbrains.mps.lang.structure" version="9" />
    <devkit ref="78434eb8-b0e5-444b-850d-e7c4ad2da9ab(jetbrains.mps.devkit.aspect.structure)" />
  </languages>
  <imports>
    <import index="tpck" ref="r:00000000-0000-4000-0000-011c89590288(jetbrains.mps.lang.core.structure)" implicit="true" />
  </imports>
  <registry>
    <language id="c72da2b9-7cce-4447-8389-f407dc1158b7" name="jetbrains.mps.lang.structure">
      <concept id="1169125787135" name="jetbrains.mps.lang.structure.structure.AbstractConceptDeclaration" flags="ig" index="PkWjJ">
        <property id="6714410169261853888" name="conceptId" index="EcuMT" />
        <property id="4628067390765956802" name="abstract" index="R5$K7" />
        <child id="1071489727083" name="linkDeclaration" index="1TKVEi" />
        <child id="1071489727084" name="propertyDeclaration" index="1TKVEl" />
      </concept>
      <concept id="1169127622168" name="jetbrains.mps.lang.structure.structure.InterfaceConceptReference" flags="ig" index="PrWs8">
        <reference id="1169127628841" name="intfc" index="PrY4T" />
      </concept>
      <concept id="1071489090640" name="jetbrains.mps.lang.structure.structure.ConceptDeclaration" flags="ig" index="1TIwiD">
        <property id="1096454100552" name="rootable" index="19KtqR" />
        <reference id="1071489389519" name="extends" index="1TJDcQ" />
        <child id="1169129564478" name="implements" index="PzmwI" />
      </concept>
      <concept id="1071489288299" name="jetbrains.mps.lang.structure.structure.PropertyDeclaration" flags="ig" index="1TJgyi">
        <property id="241647608299431129" name="propertyId" index="IQ2nx" />
        <reference id="1082985295845" name="dataType" index="AX2Wp" />
      </concept>
      <concept id="1071489288298" name="jetbrains.mps.lang.structure.structure.LinkDeclaration" flags="ig" index="1TJgyj">
        <property id="1071599776563" name="role" index="20kJfa" />
        <property id="1071599893252" name="sourceCardinality" index="20lbJX" />
        <property id="1071599937831" name="metaClass" index="20lmBu" />
        <property id="241647608299431140" name="linkId" index="IQ2ns" />
        <reference id="1071599976176" name="target" index="20lvS9" />
      </concept>
    </language>
    <language id="ceab5195-25ea-4f22-9b92-103b95ca8c0c" name="jetbrains.mps.lang.core">
      <concept id="1169194658468" name="jetbrains.mps.lang.core.structure.INamedConcept" flags="ng" index="TrEIO">
        <property id="1169194664001" name="name" index="TrG5h" />
      </concept>
    </language>
  </registry>
  <node concept="1TIwiD" id="3_cs9tOsUh0">
    <property role="EcuMT" value="4128798754188010560" />
    <property role="TrG5h" value="Lecture" />
    <ref role="1TJDcQ" to="tpck:gw2VY9q" resolve="BaseConcept" />
    <node concept="1TJgyj" id="3_cs9tOt5VW" role="1TKVEi">
      <property role="IQ2ns" value="4128798754188058364" />
      <property role="20kJfa" value="room" />
      <property role="20lbJX" value="fLJekj4/_1" />
      <ref role="20lvS9" node="3_cs9tOsUhk" resolve="Room" />
    </node>
    <node concept="1TJgyj" id="3_cs9tOt5VN" role="1TKVEi">
      <property role="IQ2ns" value="4128798754188058355" />
      <property role="20lmBu" value="fLJjDmT/aggregation" />
      <property role="20kJfa" value="schedule" />
      <property role="20lbJX" value="fLJekj4/_1" />
      <ref role="20lvS9" node="3_cs9tOsUh8" resolve="Schedule" />
    </node>
    <node concept="PrWs8" id="3_cs9tOsUh1" role="PzmwI">
      <ref role="PrY4T" to="tpck:h0TrEE$" resolve="INamedConcept" />
    </node>
    <node concept="1TJgyi" id="3_cs9tOsUh3" role="1TKVEl">
      <property role="IQ2nx" value="4128798754188010563" />
      <property role="TrG5h" value="description" />
      <ref role="AX2Wp" to="tpck:fKAOsGN" resolve="string" />
    </node>
    <node concept="1TJgyi" id="3_cs9tOsUh5" role="1TKVEl">
      <property role="IQ2nx" value="4128798754188010565" />
      <property role="TrG5h" value="maxParticipants" />
      <ref role="AX2Wp" to="tpck:fKAQMTA" resolve="integer" />
    </node>
  </node>
  <node concept="1TIwiD" id="3_cs9tOsUh8">
    <property role="EcuMT" value="4128798754188010568" />
    <property role="TrG5h" value="Schedule" />
    <property role="R5$K7" value="true" />
    <ref role="1TJDcQ" to="tpck:gw2VY9q" resolve="BaseConcept" />
    <node concept="1TJgyj" id="3_cs9tOsUhi" role="1TKVEi">
      <property role="IQ2ns" value="4128798754188010578" />
      <property role="20lmBu" value="fLJjDmT/aggregation" />
      <property role="20kJfa" value="at" />
      <property role="20lbJX" value="fLJekj4/_1" />
      <ref role="20lvS9" node="3_cs9tOsUha" resolve="DateAndTime" />
    </node>
  </node>
  <node concept="1TIwiD" id="3_cs9tOsUh9">
    <property role="EcuMT" value="4128798754188010569" />
    <property role="TrG5h" value="Recurring" />
    <ref role="1TJDcQ" node="3_cs9tOsUh8" resolve="Schedule" />
  </node>
  <node concept="1TIwiD" id="3_cs9tOsUha">
    <property role="EcuMT" value="4128798754188010570" />
    <property role="TrG5h" value="DateAndTime" />
    <ref role="1TJDcQ" to="tpck:gw2VY9q" resolve="BaseConcept" />
    <node concept="1TJgyi" id="3_cs9tOsUhb" role="1TKVEl">
      <property role="IQ2nx" value="4128798754188010571" />
      <property role="TrG5h" value="date" />
      <ref role="AX2Wp" to="tpck:fKAOsGN" resolve="string" />
    </node>
    <node concept="1TJgyi" id="3_cs9tOsUhd" role="1TKVEl">
      <property role="IQ2nx" value="4128798754188010573" />
      <property role="TrG5h" value="time" />
      <ref role="AX2Wp" to="tpck:fKAOsGN" resolve="string" />
    </node>
  </node>
  <node concept="1TIwiD" id="3_cs9tOsUhk">
    <property role="EcuMT" value="4128798754188010580" />
    <property role="TrG5h" value="Room" />
    <ref role="1TJDcQ" to="tpck:gw2VY9q" resolve="BaseConcept" />
    <node concept="1TJgyi" id="3_cs9tOsUhn" role="1TKVEl">
      <property role="IQ2nx" value="4128798754188010583" />
      <property role="TrG5h" value="maxPlaces" />
      <ref role="AX2Wp" to="tpck:fKAQMTA" resolve="integer" />
    </node>
    <node concept="1TJgyi" id="3_cs9tOsUhp" role="1TKVEl">
      <property role="IQ2nx" value="4128798754188010585" />
      <property role="TrG5h" value="hasRemoteEquipment" />
      <ref role="AX2Wp" to="tpck:fKAQMTB" resolve="boolean" />
    </node>
    <node concept="PrWs8" id="3_cs9tOsUhl" role="PzmwI">
      <ref role="PrY4T" to="tpck:h0TrEE$" resolve="INamedConcept" />
    </node>
  </node>
  <node concept="1TIwiD" id="3_cs9tOsUhs">
    <property role="EcuMT" value="4128798754188010588" />
    <property role="TrG5h" value="Rooms" />
    <property role="19KtqR" value="true" />
    <ref role="1TJDcQ" to="tpck:gw2VY9q" resolve="BaseConcept" />
    <node concept="1TJgyj" id="3_cs9tOsUht" role="1TKVEi">
      <property role="IQ2ns" value="4128798754188010589" />
      <property role="20lmBu" value="fLJjDmT/aggregation" />
      <property role="20kJfa" value="rooms" />
      <property role="20lbJX" value="fLJekj5/_0__n" />
      <ref role="20lvS9" node="3_cs9tOsUhk" resolve="Room" />
    </node>
  </node>
  <node concept="1TIwiD" id="3_cs9tOt5DC">
    <property role="EcuMT" value="4128798754188057192" />
    <property role="TrG5h" value="Courses" />
    <property role="19KtqR" value="true" />
    <ref role="1TJDcQ" to="tpck:gw2VY9q" resolve="BaseConcept" />
    <node concept="1TJgyj" id="3_cs9tOt5DD" role="1TKVEi">
      <property role="IQ2ns" value="4128798754188057193" />
      <property role="20lmBu" value="fLJjDmT/aggregation" />
      <property role="20kJfa" value="lectures" />
      <property role="20lbJX" value="fLJekj5/_0__n" />
      <ref role="20lvS9" node="3_cs9tOsUh0" resolve="Lecture" />
    </node>
  </node>
  <node concept="1TIwiD" id="3_cs9tOt6eJ">
    <property role="EcuMT" value="4128798754188059567" />
    <property role="TrG5h" value="OneOff" />
    <ref role="1TJDcQ" node="3_cs9tOsUh8" resolve="Schedule" />
  </node>
  <node concept="1TIwiD" id="1rwgWV86q61">
    <property role="EcuMT" value="1648392019017048449" />
    <property role="TrG5h" value="Student" />
    <ref role="1TJDcQ" to="tpck:gw2VY9q" resolve="BaseConcept" />
    <node concept="1TJgyi" id="1rwgWV86q62" role="1TKVEl">
      <property role="IQ2nx" value="1648392019017048450" />
      <property role="TrG5h" value="name" />
      <ref role="AX2Wp" to="tpck:fKAOsGN" resolve="string" />
    </node>
    <node concept="1TJgyi" id="1rwgWV86q66" role="1TKVEl">
      <property role="IQ2nx" value="1648392019017048454" />
      <property role="TrG5h" value="semester" />
      <ref role="AX2Wp" to="tpck:fKAQMTA" resolve="integer" />
    </node>
    <node concept="1TJgyj" id="1rwgWV86q64" role="1TKVEi">
      <property role="IQ2ns" value="1648392019017048452" />
      <property role="20lmBu" value="fLJjDmT/aggregation" />
      <property role="20kJfa" value="born" />
      <property role="20lbJX" value="fLJekj4/_1" />
      <ref role="20lvS9" node="3_cs9tOsUha" resolve="DateAndTime" />
    </node>
  </node>
  <node concept="1TIwiD" id="1rwgWV86q69">
    <property role="EcuMT" value="1648392019017048457" />
    <property role="TrG5h" value="Students" />
    <property role="19KtqR" value="true" />
    <ref role="1TJDcQ" to="tpck:gw2VY9q" resolve="BaseConcept" />
    <node concept="1TJgyj" id="1rwgWV86q6a" role="1TKVEi">
      <property role="IQ2ns" value="1648392019017048458" />
      <property role="20lmBu" value="fLJjDmT/aggregation" />
      <property role="20kJfa" value="students" />
      <property role="20lbJX" value="fLJekj5/_0__n" />
      <ref role="20lvS9" node="1rwgWV86q61" resolve="Student" />
    </node>
  </node>
  <node concept="1TIwiD" id="1rwgWV86q6c">
    <property role="EcuMT" value="1648392019017048460" />
    <property role="TrG5h" value="LectureAssignments" />
    <property role="19KtqR" value="true" />
    <ref role="1TJDcQ" to="tpck:gw2VY9q" resolve="BaseConcept" />
    <node concept="1TJgyj" id="1rwgWV86q6i" role="1TKVEi">
      <property role="IQ2ns" value="1648392019017048466" />
      <property role="20lmBu" value="fLJjDmT/aggregation" />
      <property role="20kJfa" value="lectures" />
      <property role="20lbJX" value="fLJekj5/_0__n" />
      <ref role="20lvS9" node="1rwgWV86q6f" resolve="Assignment" />
    </node>
    <node concept="1TJgyj" id="1rwgWV86q6d" role="1TKVEi">
      <property role="IQ2ns" value="1648392019017048461" />
      <property role="20kJfa" value="student" />
      <property role="20lbJX" value="fLJekj4/_1" />
      <ref role="20lvS9" node="1rwgWV86q61" resolve="Student" />
    </node>
  </node>
  <node concept="1TIwiD" id="1rwgWV86q6f">
    <property role="EcuMT" value="1648392019017048463" />
    <property role="TrG5h" value="Assignment" />
    <ref role="1TJDcQ" to="tpck:gw2VY9q" resolve="BaseConcept" />
    <node concept="1TJgyj" id="1rwgWV86q6g" role="1TKVEi">
      <property role="IQ2ns" value="1648392019017048464" />
      <property role="20kJfa" value="lecture" />
      <property role="20lbJX" value="fLJekj4/_1" />
      <ref role="20lvS9" node="3_cs9tOsUh0" resolve="Lecture" />
    </node>
  </node>
</model>

