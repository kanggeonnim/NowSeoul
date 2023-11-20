-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: hotplace
-- ------------------------------------------------------
-- Server version	8.0.34

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `places`
--

DROP TABLE IF EXISTS `places`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `places` (
  `CATEGORY` varchar(30) DEFAULT NULL,
  `NO` int DEFAULT NULL,
  `AREA_CD` varchar(30) NOT NULL,
  `AREA_NM` varchar(100) DEFAULT NULL,
  `latitude` decimal(13,10) DEFAULT NULL,
  `longitude` decimal(13,10) DEFAULT NULL,
  PRIMARY KEY (`AREA_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `places`
--

LOCK TABLES `places` WRITE;
/*!40000 ALTER TABLE `places` DISABLE KEYS */;
INSERT INTO `places` VALUES ('관광특구',1,'POI001','강남 MICE 관광특구',37.5109513700,127.0601678000),('관광특구',2,'POI002','동대문 관광특구',37.5672696700,127.0113516000),('관광특구',3,'POI003','명동 관광특구',37.5646503700,126.9820833000),('관광특구',4,'POI004','이태원 관광특구',37.5344196600,126.9962883000),('관광특구',5,'POI005','잠실 관광특구',37.5161936600,127.1113014000),('관광특구',6,'POI006','종로·청계 관광특구',37.5695827500,126.9962025000),('관광특구',7,'POI007','홍대 관광특구',37.5545805000,126.9218731000),('고궁·문화유산',8,'POI008','경복궁',37.5797350500,126.9765089000),('고궁·문화유산',9,'POI009','광화문·덕수궁',37.5703480900,126.9757962000),('고궁·문화유산',10,'POI010','보신각',37.5705850300,126.9834111000),('고궁·문화유산',11,'POI011','서울 암사동 유적',37.5606323500,127.1307591000),('고궁·문화유산',12,'POI012','창덕궁·종묘',37.5792764700,126.9934559000),('인구밀집지역',13,'POI013','가산디지털단지역',37.4808861900,126.8799448000),('인구밀집지역',14,'POI014','강남역',37.4985249900,127.0285177000),('인구밀집지역',15,'POI015','건대입구역',37.5396773200,127.0687294000),('인구밀집지역',16,'POI016','고덕역',37.5534554200,127.1548719000),('인구밀집지역',17,'POI017','고속터미널역',37.5046533200,127.0059872000),('인구밀집지역',18,'POI018','교대역',37.4925664000,127.0135188000),('인구밀집지역',19,'POI019','구로디지털단지역',37.4840533100,126.8966389000),('인구밀집지역',20,'POI020','구로역',37.5023500100,126.8821218000),('인구밀집지역',21,'POI021','군자역',37.5563161600,127.0801947000),('인구밀집지역',22,'POI022','남구로역',37.4852595300,126.8872957000),('인구밀집지역',23,'POI023','대림역',37.4926673600,126.8955428000),('인구밀집지역',24,'POI024','동대문역',37.5714811000,127.0096540000),('인구밀집지역',25,'POI025','뚝섬역',37.5482907200,127.0461373000),('인구밀집지역',26,'POI026','미아사거리역',37.6121951200,127.0307407000),('인구밀집지역',27,'POI027','발산역',37.5591512400,126.8391733000),('인구밀집지역',28,'POI028','북한산우이역',37.6626624400,127.0110644000),('인구밀집지역',29,'POI029','사당역',37.4779308200,126.9812659000),('인구밀집지역',30,'POI030','삼각지역',37.5353408600,126.9738833000),('인구밀집지역',31,'POI031','서울대입구역',37.4806130000,126.9530626000),('인구밀집지역',32,'POI032','서울식물원·마곡나루역',37.5665387100,126.8313756000),('인구밀집지역',33,'POI033','서울역',37.5566218000,126.9728994000),('인구밀집지역',34,'POI034','선릉역',37.5055384900,127.0504475000),('인구밀집지역',35,'POI035','성신여대입구역',37.5923931100,127.0168653000),('인구밀집지역',36,'POI036','수유역',37.6415922600,127.0259857000),('인구밀집지역',37,'POI037','신논현역·논현역',37.5080801200,127.0234058000),('인구밀집지역',38,'POI038','신도림역',37.5090109400,126.8902445000),('인구밀집지역',39,'POI039','신림역',37.4849046600,126.9292974000),('인구밀집지역',40,'POI040','신촌·이대역',37.5573362400,126.9386959000),('인구밀집지역',41,'POI041','양재역',37.4853394200,127.0339715000),('인구밀집지역',42,'POI042','역삼역',37.5011466200,127.0383883000),('인구밀집지역',43,'POI043','연신내역',37.6187186000,126.9208002000),('인구밀집지역',44,'POI044','오목교역·목동운동장',37.5288105400,126.8766416000),('인구밀집지역',45,'POI045','왕십리역',37.5621840600,127.0390320000),('인구밀집지역',46,'POI046','용산역',37.5300878700,126.9604948000),('인구밀집지역',47,'POI047','이태원역',37.5341858100,126.9930484000),('인구밀집지역',48,'POI048','장지역',37.4787503800,127.1232756000),('인구밀집지역',49,'POI049','장한평역',37.5618044100,127.0647858000),('인구밀집지역',50,'POI050','천호역',37.5392390200,127.1250125000),('인구밀집지역',51,'POI051','총신대입구(이수)역',37.4860023500,126.9810424000),('인구밀집지역',52,'POI052','충정로역',37.5596966100,126.9636915000),('인구밀집지역',53,'POI053','합정역',37.5493764200,126.9117350000),('인구밀집지역',54,'POI054','혜화역',37.5824816500,127.0017640000),('인구밀집지역',55,'POI055','홍대입구역 9번 출구',37.5567622700,126.9230074000),('인구밀집지역',56,'POI056','회기역',37.5905405300,127.0561618000),('발달상권',57,'POI057','4·19 카페거리',37.6452734500,127.0076736000),('발달상권',58,'POI058','가락시장',37.4934683100,127.1118957000),('발달상권',59,'POI059','가로수길',37.5211973200,127.0239258000),('발달상권',60,'POI060','광장(전통)시장',37.5700027300,126.9999038000),('발달상권',61,'POI061','김포공항',37.5622717200,126.8025995000),('발달상권',62,'POI062','낙산공원·이화마을',37.5803478000,127.0072961000),('발달상권',63,'POI063','노량진',37.5141852900,126.9448328000),('발달상권',64,'POI064','덕수궁길·정동길',37.5663574500,126.9718078000),('발달상권',65,'POI065','방배역 먹자골목',37.4826865800,126.9960491000),('발달상권',66,'POI066','북촌한옥마을',37.5834256800,126.9849801000),('발달상권',67,'POI067','서촌',37.5803978400,126.9696258000),('발달상권',68,'POI068','성수카페거리',37.5438627600,127.0566916000),('발달상권',69,'POI069','수유리 먹자골목',37.6428019000,127.0265000000),('발달상권',70,'POI070','쌍문동 맛집거리',37.6477767900,127.0333028000),('발달상권',71,'POI071','압구정로데오거리',37.5254859000,127.0386028000),('발달상권',72,'POI072','여의도',37.5247711500,126.9259501000),('발달상권',73,'POI073','연남동',37.5616178300,126.9223400000),('발달상권',74,'POI074','영등포 타임스퀘어',37.5170019800,126.9067563000),('발달상권',75,'POI075','외대앞',37.5948941100,127.0620228000),('발달상권',76,'POI076','용리단길',37.5311855700,126.9712941000),('발달상권',77,'POI077','이태원 앤틱가구거리',37.5322310600,126.9939181000),('발달상권',78,'POI078','인사동·익선동',37.5734280300,126.9871452000),('발달상권',79,'POI079','창동 신경제 중심지',37.6563051600,127.0543957000),('발달상권',80,'POI080','청담동 명품거리',37.5258320500,127.0437651000),('발달상권',81,'POI081','청량리 제기동 일대 전통시장',37.5808297500,127.0399812000),('발달상권',82,'POI082','해방촌·경리단길',37.5423704900,126.9871830000),('발달상권',83,'POI083','DDP(동대문디자인플라자)',37.5669880100,127.0102892000),('발달상권',84,'POI084','DMC(디지털미디어시티)',37.5799906900,126.8924761000),('공원',85,'POI085','강서한강공원',37.5865142400,126.8185486000),('공원',86,'POI086','고척돔',37.4976717600,126.8670226000),('공원',87,'POI087','광나루한강공원',37.5565937200,127.1238345000),('공원',88,'POI088','광화문광장',37.5734095900,126.9769210000),('공원',89,'POI089','국립중앙박물관·용산가족공원',37.5226609100,126.9810104000),('공원',90,'POI090','난지한강공원',37.5665014400,126.8773279000),('공원',91,'POI091','남산공원',37.5512802800,126.9940138000),('공원',92,'POI092','노들섬',37.5175571800,126.9586615000),('공원',93,'POI093','뚝섬한강공원',37.5286507300,127.0727210000),('공원',94,'POI094','망원한강공원',37.5525049200,126.8992731000),('공원',95,'POI095','반포한강공원',37.5092828900,126.9941323000),('공원',96,'POI096','북서울꿈의숲',37.6216759200,127.0418215000),('공원',97,'POI097','불광천',37.5873102900,126.9124587000),('공원',98,'POI098','서리풀공원·몽마르뜨공원',37.4915830700,127.0026832000),('공원',99,'POI099','서울대공원',37.4291716900,127.0165014000),('공원',100,'POI100','서울숲공원',37.5436926200,127.0386028000),('공원',101,'POI101','시청광장',37.5656572800,126.9780231000),('공원',102,'POI102','아차산',37.5668420200,127.1028107000),('공원',103,'POI103','양화한강공원',37.5413049500,126.8981844000),('공원',104,'POI104','어린이대공원',37.5490617000,127.0813610000),('공원',105,'POI105','여의도한강공원',37.5302147200,126.9306080000),('공원',106,'POI106','월드컵공원',37.5701950200,126.8833351000),('공원',107,'POI107','응봉산',37.5482144900,127.0304661000),('공원',108,'POI108','이촌한강공원',37.5199375200,126.9636195000),('공원',109,'POI109','잠실종합운동장',37.5145767600,127.0738578000),('공원',110,'POI110','잠실한강공원',37.5179633000,127.0851236000),('공원',111,'POI111','잠원한강공원',37.5238107500,127.0147290000),('공원',112,'POI112','청계산',37.4407390400,127.0500179000),('공원',113,'POI113','청와대',37.5854530000,126.9763442000);
/*!40000 ALTER TABLE `places` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-20 15:35:39
