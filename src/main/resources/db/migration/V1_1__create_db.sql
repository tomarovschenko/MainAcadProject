CREATE TABLE `сontract`(
  `id` INT ZEROFILL UNSIGNED NOT NULL AUTO_INCREMENT,
  `сontract` VARCHAR(45) NOT NULL,
  `date` DATE NOT NULL,
  `аmount` DOUBLE NOT NULL,
  `student_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  UNIQUE INDEX `сontract_UNIQUE` (`сontract` ASC));

CREATE TABLE `course`(
  `id` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `course_name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `course_name_UNIQUE` (`course_name`));

CREATE TABLE `instructor` (
  `id` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `surname` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `patronymic` varchar(45) NOT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `working` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`));

CREATE TABLE `labor_contract` (
  `id` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `labor_contract` varchar(45) NOT NULL,
  `contrDate` date NOT NULL,
  `instructor_id` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `labor_contract_UNIQUE` (`labor_contract`));

 CREATE TABLE `manager` (
  `id` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `manager_name` varchar(45) NOT NULL,
  `access` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `manager_name_UNIQUE` (`manager_name`));

CREATE TABLE `payments` (
  `id` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `сontract_id` int(11) unsigned NOT NULL,
  `plan_amount` double NOT NULL,
  `plan_date` date NOT NULL,
  `invoice_number` varchar(45) DEFAULT NULL,
  `invoice_date` varchar(45) DEFAULT NULL,
  `fact_amount` double DEFAULT NULL,
  `fact_date` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`));

CREATE TABLE `student` (
  `id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `surname` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `patronymic` varchar(45) NOT NULL,
  `phone` varchar(45) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  `address` varchar(70) DEFAULT NULL,
  `manager_id` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`));

CREATE TABLE `study_group` (
  `id` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `group_name` varchar(45) NOT NULL,
  `course_id` int(11) unsigned NOT NULL,
  `plan_startDate` date NOT NULL,
  `plan_endDate` date NOT NULL,
  `fact_startDate` date DEFAULT NULL,
  `fact_endDate` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `group_name_UNIQUE` (`group_name`));

CREATE TABLE `instructor_group` (
  `id` int(10) unsigned zerofill NOT NULL,
  `instructor_id` int(10) unsigned NOT NULL,
  `group_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `Group_idx` (`group_id`),
  KEY `Instructor_idx` (`instructor_id`),
  KEY `StadyGroup_idx` (`group_id`),
  KEY `Instr_idx` (`instructor_id`),
  CONSTRAINT `Instr` FOREIGN KEY (`instructor_id`) REFERENCES `instructor` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `StadyGroup` FOREIGN KEY (`group_id`) REFERENCES `study_group` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION);

CREATE TABLE `main_acad`.`student_group` (
  `student_id` int(10) unsigned NOT NULL,
  `group_id` int(10) unsigned NOT NULL,
  `id` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `Student_idx` (`student_id`),
  KEY `Group_idx` (`group_id`),
  CONSTRAINT `Group` FOREIGN KEY (`group_id`) REFERENCES `study_group` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `Student` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION);

ALTER TABLE `study_group`
ADD INDEX `GroupCourse_idx` (`course_id` ASC);
ALTER TABLE `study_group`
ADD CONSTRAINT `GroupCourse`
  FOREIGN KEY (`course_id`)
  REFERENCES `course` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `labor_contract`
ADD INDEX `InstructorContract_idx` (`instructor_id` ASC);
ALTER TABLE `labor_contract`
ADD CONSTRAINT `InstructorContract`
  FOREIGN KEY (`instructor_id`)
  REFERENCES `instructor` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `student`
ADD INDEX `ManagerStudent_idx` (`manager_id` ASC);
ALTER TABLE `student`
ADD CONSTRAINT `ManagerStudent`
  FOREIGN KEY (`manager_id`)
  REFERENCES `manager` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `сontract`
ADD INDEX `StudentContract_idx` (`student_id` ASC);
ALTER TABLE `сontract`
ADD CONSTRAINT `StudentContract`
  FOREIGN KEY (`student_id`)
  REFERENCES `student` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `payments`
ADD INDEX `ContractPayment_idx` (`сontract_id` ASC);
ALTER TABLE `payments`
ADD CONSTRAINT `ContractPayment`
  FOREIGN KEY (`сontract_id`)
  REFERENCES `сontract` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;



