<?xml version="1.0" encoding="UTF-8"?>
<model ref="r:ce161c54-ea76-40a6-a31d-9d7cd01febe2(University.Schedule.modelserver.backend.sandbox)">
  <persistence version="9" />
  <languages>
    <use id="96533389-8d4c-46f2-b150-8d89155f7fca" name="University.Schedule" version="0" />
  </languages>
  <imports />
  <registry>
    <language id="96533389-8d4c-46f2-b150-8d89155f7fca" name="University.Schedule">
      <concept id="4128798754188059567" name="University.Schedule.structure.OneOff" flags="ng" index="1de1cS" />
      <concept id="4128798754188057192" name="University.Schedule.structure.Courses" flags="ng" index="1de2FZ">
        <child id="4128798754188057193" name="lectures" index="1de2FY" />
      </concept>
      <concept id="4128798754188010580" name="University.Schedule.structure.Room" flags="ng" index="1dfXj3">
        <property id="4128798754188010583" name="maxPlaces" index="1dfXj0" />
        <property id="4128798754188010585" name="hasRemoteEquipment" index="1dfXje" />
      </concept>
      <concept id="4128798754188010588" name="University.Schedule.structure.Rooms" flags="ng" index="1dfXjb">
        <child id="4128798754188010589" name="rooms" index="1dfXja" />
      </concept>
      <concept id="4128798754188010560" name="University.Schedule.structure.Lecture" flags="ng" index="1dfXjn">
        <property id="4128798754188010565" name="maxParticipants" index="1dfXji" />
        <property id="4128798754188010563" name="description" index="1dfXjk" />
        <reference id="4128798754188058364" name="room" index="1de2TF" />
        <child id="4128798754188058355" name="schedule" index="1de2T$" />
      </concept>
      <concept id="4128798754188010570" name="University.Schedule.structure.DateAndTime" flags="ng" index="1dfXjt">
        <property id="4128798754188010573" name="time" index="1dfXjq" />
        <property id="4128798754188010571" name="date" index="1dfXjs" />
      </concept>
      <concept id="4128798754188010569" name="University.Schedule.structure.Recurring" flags="ng" index="1dfXju" />
      <concept id="4128798754188010568" name="University.Schedule.structure.Schedule" flags="ng" index="1dfXjv">
        <child id="4128798754188010578" name="at" index="1dfXj5" />
      </concept>
    </language>
    <language id="ceab5195-25ea-4f22-9b92-103b95ca8c0c" name="jetbrains.mps.lang.core">
      <concept id="1169194658468" name="jetbrains.mps.lang.core.structure.INamedConcept" flags="ng" index="TrEIO">
        <property id="1169194664001" name="name" index="TrG5h" />
      </concept>
    </language>
  </registry>
  <node concept="1dfXjb" id="3_cs9tOt5VE">
    <node concept="1dfXj3" id="3_cs9tOt5VF" role="1dfXja">
      <property role="TrG5h" value="Einstein" />
      <property role="1dfXj0" value="13" />
      <property role="1dfXje" value="true" />
    </node>
    <node concept="1dfXj3" id="3_cs9tOt5VH" role="1dfXja">
      <property role="TrG5h" value="Schrödinger" />
      <property role="1dfXj0" value="420" />
      <property role="1dfXje" value="true" />
    </node>
  </node>
  <node concept="1de2FZ" id="3_cs9tOt5VK">
    <node concept="1dfXjn" id="3_cs9tOt5VL" role="1de2FY">
      <property role="TrG5h" value="Physics 101" />
      <property role="1dfXjk" value="You learn about stuff" />
      <property role="1dfXji" value="42" />
      <ref role="1de2TF" node="3_cs9tOt5VF" resolve="Einstein" />
      <node concept="1dfXju" id="3_cs9tOt6eE" role="1de2T$">
        <node concept="1dfXjt" id="3_cs9tOt6eF" role="1dfXj5">
          <property role="1dfXjs" value="08.03.2022" />
          <property role="1dfXjq" value="11:30:00" />
        </node>
      </node>
    </node>
    <node concept="1dfXjn" id="3_cs9tOt6yQ" role="1de2FY">
      <property role="TrG5h" value="New Students Welcome" />
      <property role="1dfXjk" value="Hello everyone" />
      <property role="1dfXji" value="69" />
      <ref role="1de2TF" node="3_cs9tOt5VH" resolve="Schrödinger" />
      <node concept="1de1cS" id="3_cs9tOt6yZ" role="1de2T$">
        <node concept="1dfXjt" id="3_cs9tOt6z2" role="1dfXj5">
          <property role="1dfXjs" value="08.03.2022" />
          <property role="1dfXjq" value="08:00:00" />
        </node>
      </node>
    </node>
  </node>
</model>

