INSERT INTO permissions (code)
VALUES
	('A'),
	('B'),
	('C');
	
	
INSERT INTO role_permission (role_id,permission_id)
SELECT r.id,p.id from roles r,permissions p;