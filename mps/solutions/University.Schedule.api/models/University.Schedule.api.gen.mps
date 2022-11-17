<?xml version="1.0" encoding="UTF-8"?>
<model ref="r:86be3a58-5d45-4d2b-aadb-835f83eeb67b(University.Schedule.api.gen)">
  <persistence version="9" />
  <languages>
    <use id="a10f7b95-bc3f-4a13-a29d-1610dbeea4c1" name="org.modelix.mps.apigen" version="0" />
  </languages>
  <imports />
  <registry>
    <language id="a10f7b95-bc3f-4a13-a29d-1610dbeea4c1" name="org.modelix.mps.apigen">
      <concept id="4946168017312726367" name="org.modelix.mps.apigen.structure.LanguageInclude" flags="ng" index="1jXIN4">
        <child id="4946168017312726368" name="ref" index="1jXINV" />
      </concept>
      <concept id="4946168017312726366" name="org.modelix.mps.apigen.structure.ApiDefinition" flags="ng" index="1jXIN5">
        <child id="6054934760640729222" name="languages" index="3e8aej" />
      </concept>
    </language>
    <language id="7866978e-a0f0-4cc7-81bc-4d213d9375e1" name="jetbrains.mps.lang.smodel">
      <concept id="3542851458883438784" name="jetbrains.mps.lang.smodel.structure.LanguageId" flags="nn" index="2V$Bhx">
        <property id="3542851458883439831" name="namespace" index="2V$B1Q" />
        <property id="3542851458883439832" name="languageId" index="2V$B1T" />
      </concept>
    </language>
  </registry>
  <node concept="1jXIN5" id="7qrA4FyOm6o">
    <node concept="1jXIN4" id="7qrA4FyOm6q" role="3e8aej">
      <node concept="2V$Bhx" id="7qrA4FyOm6p" role="1jXINV">
        <property role="2V$B1T" value="96533389-8d4c-46f2-b150-8d89155f7fca" />
        <property role="2V$B1Q" value="University.Schedule" />
      </node>
    </node>
    <node concept="1jXIN4" id="7jS7ZYbgYyI" role="3e8aej">
      <node concept="2V$Bhx" id="7jS7ZYbgYyO" role="1jXINV">
        <property role="2V$B1T" value="0a7577d1-d4e5-431d-98b1-fae38f9aee80" />
        <property role="2V$B1Q" value="org.modelix.model.repositoryconcepts" />
      </node>
    </node>
  </node>
</model>

