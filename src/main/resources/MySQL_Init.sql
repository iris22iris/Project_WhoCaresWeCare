DROP TABLE IF EXISTS MemberHLab01; 

CREATE TABLE MemberHLab01(
	pk   INT NOT NULL AUTO_INCREMENT,
	id   varchar(32) NULL,
	pswd varchar(64) NULL,
	NAME varchar(50) NULL,
	PRIMARY KEY(pk)
);

INSERT INTO MemberHLab01 VALUES 
(NULL, 'kitty', 'k123', '凱蒂貓_mysql'), 
(NULL, 'micky', 'm456', '米小薯_mysql'), 
(NULL, 'snoopy', 's789', '史努比_mysql')
