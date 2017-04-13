ALTER TABLE `study_group`
ADD COLUMN `spent_hourse` DOUBLE NULL AFTER `fact_endDate`,
ADD COLUMN `total_hourse` DOUBLE NOT NULL AFTER `spent_hourse`;

ALTER TABLE `student`
ADD COLUMN `passport` varchar(45) NULL AFTER `phone`;

ALTER TABLE `instructor`
ADD COLUMN `passport` varchar(45) NULL AFTER `phone`;

ALTER TABLE `сontract`
ADD COLUMN `date_end` DATE NULL;

ALTER TABLE `labor_contract`
ADD COLUMN `date_end` DATE NULL,
CHANGE COLUMN `labor_contract` `number` VARCHAR(45) NOT NULL ,
CHANGE COLUMN `contrDate` `date_start` DATE NOT NULL ;


CREATE TABLE `registers` (
  `id` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `lesson_date` date NOT NULL,
  `spent_hours` DOUBLE NOT NULL,
  `study_group_id` INT(10) UNSIGNED ZEROFILL NOT NULL ,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`));

ALTER TABLE `registers`
ADD INDEX `Study_groupRegisters_idx` (`study_group_id` ASC);
ALTER TABLE `registers`
ADD CONSTRAINT `Study_groupRegisters`
  FOREIGN KEY (`study_group_id`)
  REFERENCES `study_group` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;