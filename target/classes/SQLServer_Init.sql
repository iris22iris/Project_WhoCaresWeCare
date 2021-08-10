IF OBJECT_ID('dbo.MemberHLab01', 'U') IS NOT NULL 
  DROP TABLE dbo.MemberHLab01; 

CREATE TABLE MemberHLab01(
	pk   int NOT NULL IDENTITY,
	id   varchar(32) NULL,
	pswd varchar(64) NULL,
	name varchar(50) NULL,
	PRIMARY KEY(pk)
)

INSERT INTO MemberHLab01 VALUES 
('kitty', 'k123', '凱蒂貓_mssql'), 
('micky', 'm456', '米小薯_mssql'), 
('snoopy', 's789', '史努比_mssql')
