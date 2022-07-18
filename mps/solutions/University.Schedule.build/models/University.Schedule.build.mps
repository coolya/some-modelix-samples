<?xml version="1.0" encoding="UTF-8"?>
<model ref="r:8ad1cf69-2406-4b21-99a3-e53b002da60c(University.Schedule.build)">
  <persistence version="9" />
  <languages>
    <use id="798100da-4f0a-421a-b991-71f8c50ce5d2" name="jetbrains.mps.build" version="0" />
    <use id="0cf935df-4699-4e9c-a132-fa109541cba3" name="jetbrains.mps.build.mps" version="7" />
  </languages>
  <imports>
    <import index="ffeo" ref="r:874d959d-e3b4-4d04-b931-ca849af130dd(jetbrains.mps.ide.build)" />
    <import index="tf2h" ref="r:4aabb3c5-d03c-4c1f-94a0-b9560a7e1d05(org.modelix.mps.api-gen.build)" />
  </imports>
  <registry>
    <language id="798100da-4f0a-421a-b991-71f8c50ce5d2" name="jetbrains.mps.build">
      <concept id="5481553824944787378" name="jetbrains.mps.build.structure.BuildSourceProjectRelativePath" flags="ng" index="55IIr" />
      <concept id="7321017245476976379" name="jetbrains.mps.build.structure.BuildRelativePath" flags="ng" index="iG8Mu">
        <child id="7321017245477039051" name="compositePart" index="iGT6I" />
      </concept>
      <concept id="4993211115183325728" name="jetbrains.mps.build.structure.BuildProjectDependency" flags="ng" index="2sgV4H">
        <reference id="5617550519002745380" name="script" index="1l3spb" />
        <child id="4129895186893471026" name="artifacts" index="2JcizS" />
      </concept>
      <concept id="8618885170173601777" name="jetbrains.mps.build.structure.BuildCompositePath" flags="nn" index="2Ry0Ak">
        <property id="8618885170173601779" name="head" index="2Ry0Am" />
        <child id="8618885170173601778" name="tail" index="2Ry0An" />
      </concept>
      <concept id="6647099934206700647" name="jetbrains.mps.build.structure.BuildJavaPlugin" flags="ng" index="10PD9b" />
      <concept id="7389400916848136194" name="jetbrains.mps.build.structure.BuildFolderMacro" flags="ng" index="398rNT">
        <child id="7389400916848144618" name="defaultPath" index="398pKh" />
      </concept>
      <concept id="7389400916848153117" name="jetbrains.mps.build.structure.BuildSourceMacroRelativePath" flags="ng" index="398BVA">
        <reference id="7389400916848153130" name="macro" index="398BVh" />
      </concept>
      <concept id="5617550519002745364" name="jetbrains.mps.build.structure.BuildLayout" flags="ng" index="1l3spV" />
      <concept id="5617550519002745363" name="jetbrains.mps.build.structure.BuildProject" flags="ng" index="1l3spW">
        <property id="5204048710541015587" name="internalBaseDirectory" index="2DA0ip" />
        <child id="6647099934206700656" name="plugins" index="10PD9s" />
        <child id="7389400916848080626" name="parts" index="3989C9" />
        <child id="5617550519002745381" name="dependencies" index="1l3spa" />
        <child id="5617550519002745378" name="macros" index="1l3spd" />
        <child id="5617550519002745372" name="layout" index="1l3spN" />
      </concept>
      <concept id="8654221991637384182" name="jetbrains.mps.build.structure.BuildFileIncludesSelector" flags="ng" index="3qWCbU">
        <property id="8654221991637384184" name="pattern" index="3qWCbO" />
      </concept>
      <concept id="4701820937132344003" name="jetbrains.mps.build.structure.BuildLayout_Container" flags="ng" index="1y1bJS">
        <child id="7389400916848037006" name="children" index="39821P" />
      </concept>
      <concept id="5248329904287794596" name="jetbrains.mps.build.structure.BuildInputFiles" flags="ng" index="3LXTmp">
        <child id="5248329904287794598" name="dir" index="3LXTmr" />
        <child id="5248329904287794679" name="selectors" index="3LXTna" />
      </concept>
    </language>
    <language id="ceab5195-25ea-4f22-9b92-103b95ca8c0c" name="jetbrains.mps.lang.core">
      <concept id="1169194658468" name="jetbrains.mps.lang.core.structure.INamedConcept" flags="ng" index="TrEIO">
        <property id="1169194664001" name="name" index="TrG5h" />
      </concept>
    </language>
    <language id="0cf935df-4699-4e9c-a132-fa109541cba3" name="jetbrains.mps.build.mps">
      <concept id="1500819558095907805" name="jetbrains.mps.build.mps.structure.BuildMps_Group" flags="ng" index="2G$12M">
        <child id="1500819558095907806" name="modules" index="2G$12L" />
      </concept>
      <concept id="1265949165890536423" name="jetbrains.mps.build.mps.structure.BuildMpsLayout_ModuleJars" flags="ng" index="L2wRC">
        <reference id="1265949165890536425" name="module" index="L2wRA" />
      </concept>
      <concept id="868032131020265945" name="jetbrains.mps.build.mps.structure.BuildMPSPlugin" flags="ng" index="3b7kt6" />
      <concept id="5253498789149381388" name="jetbrains.mps.build.mps.structure.BuildMps_Module" flags="ng" index="3bQrTs">
        <child id="5253498789149547825" name="sources" index="3bR31x" />
        <child id="5253498789149547704" name="dependencies" index="3bR37C" />
      </concept>
      <concept id="763829979718664966" name="jetbrains.mps.build.mps.structure.BuildMps_ModuleResources" flags="ng" index="3rtmxn">
        <child id="763829979718664967" name="files" index="3rtmxm" />
      </concept>
      <concept id="5507251971038816436" name="jetbrains.mps.build.mps.structure.BuildMps_Generator" flags="ng" index="1yeLz9" />
      <concept id="4278635856200817744" name="jetbrains.mps.build.mps.structure.BuildMps_ModuleModelRoot" flags="ng" index="1BupzO">
        <property id="8137134783396907368" name="convert2binary" index="1Hdu6h" />
        <property id="8137134783396676838" name="extracted" index="1HemKv" />
        <property id="2889113830911481881" name="deployFolderName" index="3ZfqAx" />
        <child id="8137134783396676835" name="location" index="1HemKq" />
      </concept>
      <concept id="3189788309731840247" name="jetbrains.mps.build.mps.structure.BuildMps_Solution" flags="ng" index="1E1JtA" />
      <concept id="3189788309731840248" name="jetbrains.mps.build.mps.structure.BuildMps_Language" flags="ng" index="1E1JtD">
        <child id="9200313594498201639" name="generator" index="1TViLv" />
      </concept>
      <concept id="3189788309731922642" name="jetbrains.mps.build.mps.structure.BuildMps_ModuleDependencyUseLanguage" flags="ng" index="1E1Vl3">
        <reference id="3189788309731922643" name="language" index="1E1Vl2" />
      </concept>
      <concept id="322010710375871467" name="jetbrains.mps.build.mps.structure.BuildMps_AbstractModule" flags="ng" index="3LEN3z">
        <property id="8369506495128725901" name="compact" index="BnDLt" />
        <property id="322010710375892619" name="uuid" index="3LESm3" />
        <child id="322010710375956261" name="path" index="3LF7KH" />
      </concept>
    </language>
  </registry>
  <node concept="1l3spW" id="7qrA4FyO$JZ">
    <property role="TrG5h" value="modelix-example" />
    <property role="2DA0ip" value="../.." />
    <node concept="10PD9b" id="7qrA4FyO$K0" role="10PD9s" />
    <node concept="3b7kt6" id="7qrA4FyO$K1" role="10PD9s" />
    <node concept="398rNT" id="7qrA4FyO$K2" role="1l3spd">
      <property role="TrG5h" value="mps_home" />
      <node concept="55IIr" id="7qrA4FyO$Ub" role="398pKh" />
    </node>
    <node concept="398rNT" id="7qrA4FyO$Un" role="1l3spd">
      <property role="TrG5h" value="dependencies" />
      <node concept="55IIr" id="7qrA4FyO$Us" role="398pKh">
        <node concept="2Ry0Ak" id="7qrA4FyO$Ux" role="iGT6I">
          <property role="2Ry0Am" value="build" />
          <node concept="2Ry0Ak" id="7qrA4FyO$UA" role="2Ry0An">
            <property role="2Ry0Am" value="dependencies" />
          </node>
        </node>
      </node>
    </node>
    <node concept="2sgV4H" id="7qrA4FyO$K3" role="1l3spa">
      <ref role="1l3spb" to="ffeo:3IKDaVZmzS6" resolve="mps" />
      <node concept="398BVA" id="7qrA4FyO$K4" role="2JcizS">
        <ref role="398BVh" node="7qrA4FyO$K2" resolve="mps_home" />
      </node>
    </node>
    <node concept="2sgV4H" id="7qrA4FyOY7u" role="1l3spa">
      <ref role="1l3spb" to="tf2h:7Q4ofxJ_nlt" resolve="org.modelix.mps.api-gen" />
      <node concept="398BVA" id="7qrA4FyOY7z" role="2JcizS">
        <ref role="398BVh" node="7qrA4FyO$Un" resolve="dependencies" />
      </node>
    </node>
    <node concept="1l3spV" id="7qrA4FyO$Ky" role="1l3spN">
      <node concept="L2wRC" id="7qrA4FyO$Lx" role="39821P">
        <ref role="L2wRA" node="7qrA4FyO$Kd" resolve="University.Schedule" />
      </node>
      <node concept="L2wRC" id="7qrA4FyP1_N" role="39821P">
        <ref role="L2wRA" node="7qrA4FyOY4$" resolve="University.Schedule.api" />
      </node>
    </node>
    <node concept="2G$12M" id="7qrA4FyO$Kk" role="3989C9">
      <property role="TrG5h" value="modelix-example" />
      <node concept="1E1JtA" id="7qrA4FyOY4$" role="2G$12L">
        <property role="BnDLt" value="true" />
        <property role="TrG5h" value="University.Schedule.api" />
        <property role="3LESm3" value="08d2eb60-9d5d-41c4-b815-4abbc32078c1" />
        <node concept="55IIr" id="7qrA4FyOY55" role="3LF7KH">
          <node concept="2Ry0Ak" id="7qrA4FyOY5b" role="iGT6I">
            <property role="2Ry0Am" value="solutions" />
            <node concept="2Ry0Ak" id="7qrA4FyOY5g" role="2Ry0An">
              <property role="2Ry0Am" value="University.Schedule.api" />
              <node concept="2Ry0Ak" id="7qrA4FyOY5l" role="2Ry0An">
                <property role="2Ry0Am" value="University.Schedule.api.msd" />
              </node>
            </node>
          </node>
        </node>
        <node concept="1BupzO" id="7qrA4FyOY5v" role="3bR31x">
          <property role="3ZfqAx" value="models" />
          <property role="1Hdu6h" value="true" />
          <property role="1HemKv" value="true" />
          <node concept="3LXTmp" id="7qrA4FyOY5w" role="1HemKq">
            <node concept="398BVA" id="7qrA4FyOY5n" role="3LXTmr">
              <ref role="398BVh" node="7qrA4FyO$K2" resolve="mps_home" />
              <node concept="2Ry0Ak" id="7qrA4FyOY5o" role="iGT6I">
                <property role="2Ry0Am" value="solutions" />
                <node concept="2Ry0Ak" id="7qrA4FyOY5p" role="2Ry0An">
                  <property role="2Ry0Am" value="University.Schedule.api" />
                  <node concept="2Ry0Ak" id="7qrA4FyOY5q" role="2Ry0An">
                    <property role="2Ry0Am" value="models" />
                  </node>
                </node>
              </node>
            </node>
            <node concept="3qWCbU" id="7qrA4FyOY5x" role="3LXTna">
              <property role="3qWCbO" value="**/*.mps, **/*.mpsr, **/.model" />
            </node>
          </node>
        </node>
        <node concept="1E1Vl3" id="1k5KCiRZlVh" role="3bR37C">
          <ref role="1E1Vl2" node="7qrA4FyO$Kd" resolve="University.Schedule" />
        </node>
      </node>
      <node concept="1E1JtD" id="7qrA4FyO$Kd" role="2G$12L">
        <property role="BnDLt" value="true" />
        <property role="TrG5h" value="University.Schedule" />
        <property role="3LESm3" value="96533389-8d4c-46f2-b150-8d89155f7fca" />
        <node concept="55IIr" id="7qrA4FyO$K8" role="3LF7KH">
          <node concept="2Ry0Ak" id="7qrA4FyO$K9" role="iGT6I">
            <property role="2Ry0Am" value="languages" />
            <node concept="2Ry0Ak" id="7qrA4FyO$Ka" role="2Ry0An">
              <property role="2Ry0Am" value="University.Schedule" />
              <node concept="2Ry0Ak" id="7qrA4FyO$Kb" role="2Ry0An">
                <property role="2Ry0Am" value="University.Schedule.mpl" />
              </node>
            </node>
          </node>
        </node>
        <node concept="1BupzO" id="7qrA4FyO$KG" role="3bR31x">
          <property role="3ZfqAx" value="models" />
          <property role="1Hdu6h" value="true" />
          <property role="1HemKv" value="true" />
          <node concept="3LXTmp" id="7qrA4FyOGFm" role="1HemKq">
            <node concept="398BVA" id="7qrA4FyOGFe" role="3LXTmr">
              <ref role="398BVh" node="7qrA4FyO$K2" resolve="mps_home" />
              <node concept="2Ry0Ak" id="7qrA4FyOGFf" role="iGT6I">
                <property role="2Ry0Am" value="languages" />
                <node concept="2Ry0Ak" id="7qrA4FyOGFg" role="2Ry0An">
                  <property role="2Ry0Am" value="University.Schedule" />
                  <node concept="2Ry0Ak" id="7qrA4FyOGFh" role="2Ry0An">
                    <property role="2Ry0Am" value="models" />
                  </node>
                </node>
              </node>
            </node>
            <node concept="3qWCbU" id="7qrA4FyOGFn" role="3LXTna">
              <property role="3qWCbO" value="**/*.mps, **/*.mpsr, **/.model" />
            </node>
          </node>
        </node>
        <node concept="1yeLz9" id="7qrA4FyO$KJ" role="1TViLv">
          <property role="TrG5h" value="University.Schedule.generator" />
          <property role="3LESm3" value="6e053dc9-1a7c-4bb5-aff8-1a016133b5c6" />
          <node concept="1BupzO" id="7qrA4FyO$KP" role="3bR31x">
            <property role="3ZfqAx" value="generator/templates" />
            <property role="1Hdu6h" value="true" />
            <property role="1HemKv" value="true" />
            <node concept="3LXTmp" id="7qrA4FyOGFy" role="1HemKq">
              <node concept="398BVA" id="7qrA4FyOGFo" role="3LXTmr">
                <ref role="398BVh" node="7qrA4FyO$K2" resolve="mps_home" />
                <node concept="2Ry0Ak" id="7qrA4FyOGFp" role="iGT6I">
                  <property role="2Ry0Am" value="languages" />
                  <node concept="2Ry0Ak" id="7qrA4FyOGFq" role="2Ry0An">
                    <property role="2Ry0Am" value="University.Schedule" />
                    <node concept="2Ry0Ak" id="7qrA4FyOGFr" role="2Ry0An">
                      <property role="2Ry0Am" value="generator" />
                      <node concept="2Ry0Ak" id="7qrA4FyOGFs" role="2Ry0An">
                        <property role="2Ry0Am" value="templates" />
                      </node>
                    </node>
                  </node>
                </node>
              </node>
              <node concept="3qWCbU" id="7qrA4FyOGFz" role="3LXTna">
                <property role="3qWCbO" value="**/*.mps, **/*.mpsr, **/.model" />
              </node>
            </node>
          </node>
        </node>
        <node concept="3rtmxn" id="7qrA4FyO$LE" role="3bR31x">
          <node concept="3LXTmp" id="7qrA4FyO$LF" role="3rtmxm">
            <node concept="55IIr" id="7qrA4FyO$LG" role="3LXTmr">
              <node concept="2Ry0Ak" id="7qrA4FyO$LH" role="iGT6I">
                <property role="2Ry0Am" value="languages" />
                <node concept="2Ry0Ak" id="7qrA4FyO$LI" role="2Ry0An">
                  <property role="2Ry0Am" value="University.Schedule" />
                </node>
              </node>
            </node>
            <node concept="3qWCbU" id="7qrA4FyO$LK" role="3LXTna">
              <property role="3qWCbO" value="icons/**, resources/**" />
            </node>
          </node>
        </node>
      </node>
    </node>
  </node>
</model>

