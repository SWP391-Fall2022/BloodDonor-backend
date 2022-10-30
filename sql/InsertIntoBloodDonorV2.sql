use [BloodDonorProject]

--Insert into user table
    DBCC CHECKIDENT ('[USER]', RESEED, 0);
--admin
INSERT [dbo].[User] ([Username], [Password], [Phone], [Email], [DistrictId], [AddressDetails], [Role], [Status], [Enabled]) VALUES (N'admin', N'$argon2id$v=19$m=4096,t=3,p=1$xtSaHTBbiFWYnyamxghQNw$5LU+vk3i041s0kzQKWuFbTgKmghWEHRAffceHQIUP4c', N'0999999999', N'medichorvn@gmail.com', 1, NULL, N'ADMIN', 1, 1)
--organization
insert into [USER]
values ('viettien1602', '$argon2id$v=19$m=4096,t=3,p=1$U0G5AThhp/qc56DvI9gQ0Q$0zeVwC4pTYCa97Fq06riwPE5umxCfjT5EUiFV+d0lbM', 0123456789, 'viettien1602@gmail.com', 1, 'This is the address details', 'ORGANIZATION', 1, 1)
insert into [USER]
values ('demo1', '$argon2id$v=19$m=4096,t=3,p=1$U0G5AThhp/qc56DvI9gQ0Q$0zeVwC4pTYCa97Fq06riwPE5umxCfjT5EUiFV+d0lbM', 0123456789, 'invalidemail1@gmail.com', 2, 'This is the address details', 'ORGANIZATION', 1, 1)
insert into [USER]
values ('demo2', '$argon2id$v=19$m=4096,t=3,p=1$U0G5AThhp/qc56DvI9gQ0Q$0zeVwC4pTYCa97Fq06riwPE5umxCfjT5EUiFV+d0lbM', 0123456789, 'invalidemail2@gmail.com', 2, 'This is the address details', 'ORGANIZATION', 1, 1)
insert into [USER]
values ('demo3', '$argon2id$v=19$m=4096,t=3,p=1$U0G5AThhp/qc56DvI9gQ0Q$0zeVwC4pTYCa97Fq06riwPE5umxCfjT5EUiFV+d0lbM', 0123456789, 'invalidemail3@gmail.com', 3, 'This is the address details', 'ORGANIZATION', 1, 1)
insert into [USER]
values ('demo4', '$argon2id$v=19$m=4096,t=3,p=1$U0G5AThhp/qc56DvI9gQ0Q$0zeVwC4pTYCa97Fq06riwPE5umxCfjT5EUiFV+d0lbM', 0123456789, 'invalidemail4@gmail.com', 3, 'This is the address details', 'ORGANIZATION', 1, 1)
insert into [USER]
values ('demo5', '$argon2id$v=19$m=4096,t=3,p=1$U0G5AThhp/qc56DvI9gQ0Q$0zeVwC4pTYCa97Fq06riwPE5umxCfjT5EUiFV+d0lbM', 0123456789, 'invalidemail5@gmail.com', 1, 'This is the address details', 'ORGANIZATION', 1, 1)
insert into [USER]
values ('demo6', '$argon2id$v=19$m=4096,t=3,p=1$U0G5AThhp/qc56DvI9gQ0Q$0zeVwC4pTYCa97Fq06riwPE5umxCfjT5EUiFV+d0lbM', 0123456789, 'invalidemail6@gmail.com', 1, 'This is the address details', 'ORGANIZATION', 1, 1)
insert into [USER]
values ('demo7', '$argon2id$v=19$m=4096,t=3,p=1$U0G5AThhp/qc56DvI9gQ0Q$0zeVwC4pTYCa97Fq06riwPE5umxCfjT5EUiFV+d0lbM', 0123456789, 'invalidemail7@gmail.com', 4, 'This is the address details', 'ORGANIZATION', 1, 1)
insert into [USER]
values ('demo8', '$argon2id$v=19$m=4096,t=3,p=1$U0G5AThhp/qc56DvI9gQ0Q$0zeVwC4pTYCa97Fq06riwPE5umxCfjT5EUiFV+d0lbM', 0123456789, 'invalidemail8@gmail.com', 4, 'This is the address details', 'ORGANIZATION', 1, 1)
insert into [USER]
values ('demo9', '$argon2id$v=19$m=4096,t=3,p=1$U0G5AThhp/qc56DvI9gQ0Q$0zeVwC4pTYCa97Fq06riwPE5umxCfjT5EUiFV+d0lbM', 0123456789, 'invalidemail9@gmail.com', 5, 'This is the address details', 'ORGANIZATION', 1, 1)
--donor
INSERT [dbo].[User] ([Username], [Password], [Phone], [Email], [DistrictId], [AddressDetails], [Role], [Status], [Enabled]) VALUES (N'donor', N'$argon2id$v=19$m=4096,t=3,p=1$xtSaHTBbiFWYnyamxghQNw$5LU+vk3i041s0kzQKWuFbTgKmghWEHRAffceHQIUP4c', N'01328658489', N'donor@gmail.com', 1, N'Vinhomes Central Park, phường XXX', N'DONOR', 1, 1)
INSERT [dbo].[User] ([Username], [Password], [Phone], [Email], [DistrictId], [AddressDetails], [Role], [Status], [Enabled]) VALUES (N'donor4', N'$argon2id$v=19$m=4096,t=3,p=1$xtSaHTBbiFWYnyamxghQNw$5LU+vk3i041s0kzQKWuFbTgKmghWEHRAffceHQIUP4c', N'01328658489', N'donor4@gmail.com', 2, null, N'DONOR', 1, 1)
INSERT [dbo].[User] ([Username], [Password], [Phone], [Email], [DistrictId], [AddressDetails], [Role], [Status], [Enabled]) VALUES (N'donor5', N'$argon2id$v=19$m=4096,t=3,p=1$xtSaHTBbiFWYnyamxghQNw$5LU+vk3i041s0kzQKWuFbTgKmghWEHRAffceHQIUP4c', N'01328658489', N'donor5@gmail.com', 3, null, N'DONOR', 1, 1)

--insert into donor
INSERT [dbo].[Donor] ([UserId], [Name], [Birthday], [Sex], [IdentityNum], [Avatar], [BloodType], [Anamnesis]) VALUES (12, N'Nguyễn Thị Minh B', CAST(N'2002-02-22' AS Date), N'FEMALE', N'011866654855', NULL, 'O', 'The quick brown fox jumps over the lazy dog')
INSERT [dbo].[Donor] ([UserId], [Name], [Birthday], [Sex], [IdentityNum], [Avatar], [BloodType], [Anamnesis]) VALUES (13, N'Nguyễn Văn A', CAST(N'2002-02-22' AS Date), N'MALE', N'65415631486', NULL, 'AB', null)
INSERT [dbo].[Donor] ([UserId], [Name], [Birthday], [Sex], [IdentityNum], [Avatar], [BloodType], [Anamnesis]) VALUES (14, N'Nguyễn Thị Minh B', CAST(N'2002-02-22' AS Date), N'FEMALE', N'7413214668', NULL, 'O', null)

--insert into organization
insert into Organization
values (1, 'Benh vien Ngo Viet Tien Main', '0792020304', 'APPROVED', null, null, 'This is the introduction')
insert into Organization
values (2, 'Benh vien Demo 1', '0123456789', 'APPROVED', null, null, 'This is the introduction')
insert into Organization
values (3, 'Benh vien Demo 2', '0123456789', 'APPROVED', null, null, 'This is the introduction')
insert into Organization
values (4, 'Benh vien Demo 3', '0123456789', 'APPROVED', null, null, 'This is the introduction')
insert into Organization
values (5, 'Benh vien Demo 4', '0123456789', 'APPROVED', null, null, 'This is the introduction')
insert into Organization
values (6, 'Benh vien Demo 5', '0123456789', 'APPROVED', null, null, 'This is the introduction')
insert into Organization
values (7, 'Benh vien Demo 6', '0123456789', 'APPROVED', null, null, 'This is the introduction')
insert into Organization
values (8, 'Benh vien Demo 7', '0123456789', 'APPROVED', null, null, 'This is the introduction')
insert into Organization
values (9, 'Benh vien Demo 8', '0123456789', 'APPROVED', null, null, 'This is the introduction')
insert into Organization
values (10, 'Benh vien Demo 9', '0123456789', 'APPROVED', null, null, 'This is the introduction')

--insert into verification code
insert into VerificationCode
values (1, '363651', '2022-10-03 10:37:58.160', '2022-10-03 10:52:58.160', 1)

--insert into campaign
DBCC CHECKIDENT ('Campaign', RESEED, 0);
insert into Campaign
values ('Campaign 1 of benh vien Ngo Viet Tien Main', null, 'This is the description of the campaign 1',
        '2022-10-23', '2022-11-20', 0, 1, 1, 'This is the address details', 1, 'A-B-AB-O', '2022-10-30 2022-11-10')
insert into Campaign
values ('Campaign 2 of benh vien Ngo Viet Tien Main', null, 'This is the description of the campaign 2',
    '2022-11-30', '2022-12-20', 0, 1, 1, 'This is the address details', 1, 'A-B-AB-O', '2022-10-30 2022-11-10')
insert into Campaign
values ('Campaign 3 of benh vien Ngo Viet Tien Main', null, 'This is the description of the campaign 3',
    '2022-10-25', '2022-11-12', 0, 1, 2, 'This is the address details', 1, 'A-B-AB-O', '2022-10-30 2022-11-10')
insert into Campaign
values ('Campaign 4 of benh vien Ngo Viet Tien Main', null, 'This is the description of the campaign 4',
    '2022-11-20', '2022-12-20', 0, 1, 2, 'This is the address details', 1, 'A-B-AB-O', '2022-10-30 2022-11-10')
insert into Campaign
values ('Campaign 5 of benh vien Ngo Viet Tien Main', null, 'This is the description of the campaign 5',
    '2022-10-23', '2022-11-20', 0, 1, 3, 'This is the address details', 1, 'A-B-AB-O', '2022-10-30 2022-11-10')
insert into Campaign
values ('Campaign 6 of benh vien Ngo Viet Tien Main', null, 'This is the description of the campaign 6',
    '2022-10-23', null, 1, 1, 3, 'This is the address details', 1, 'A-B', null)
insert into Campaign
values ('Normal Campaign demo 1', null, 'This is the description of the campaign demo', '2022-10-23', '2022-11-20', 0,
    1, 4, 'This is the address details', 2, 'A-B-AB-O', '2022-10-30 2022-11-10')
insert into Campaign
values ('Normal Campaign demo 2', null, 'This is the description of the campaign demo', '2022-10-23', '2022-11-20', 0,
    1, 4, 'This is the address details', 3, 'A-B-AB-O', '2022-10-30 2022-11-10')
insert into Campaign
values ('Normal Campaign demo 3', null, 'This is the description of the campaign demo', '2022-10-23', '2022-11-20', 0,
    1, 4, 'This is the address details', 4, 'A-B-AB-O', '2022-10-30 2022-11-10')
insert into Campaign
values ('Normal Campaign demo 4', null, 'This is the description of the campaign demo', '2022-10-23', '2022-11-20', 0,
    1, 4, 'This is the address details', 5, 'A-B-AB-O', '2022-10-30 2022-11-10')
insert into Campaign
values ('Normal Campaign demo 5', null, 'This is the description of the campaign demo', '2022-10-23', '2022-11-20', 0,
    1, 4, 'This is the address details', 6, 'A-B-AB-O', '2022-10-30 2022-11-10')
insert into Campaign
values ('Normal Campaign demo 6', null, 'This is the description of the campaign demo', '2022-10-23', '2022-11-20', 0,
    1, 4, 'This is the address details', 7, 'A-B-AB-O', '2022-10-30 2022-11-10')
insert into Campaign
values ('Normal Campaign demo 7', null, 'This is the description of the campaign demo', '2022-10-23', '2022-11-20', 0,
    1, 4, 'This is the address details', 8, 'A-B-AB-O', '2022-10-30 2022-11-10')
insert into Campaign
values ('Normal Campaign demo 8', null, 'This is the description of the campaign demo', '2022-10-23', '2022-11-20', 0,
    1, 4, 'This is the address details', 9, 'A-B-AB-O', '2022-10-30 2022-11-10')
insert into Campaign
values ('Normal Campaign demo 9', null, 'This is the description of the campaign demo', '2022-10-23', '2022-11-20', 0,
    1, 4, 'This is the address details', 10, 'A-B-AB-O', '2022-10-30 2022-11-10')
insert into Campaign
values ('Urgent Campaign demo 1', null, 'This is the description of the urgent campaign demo', '2022-10-23', null, 0,
    1, 5, 'This is the address details', 2, 'AB-O', NULL)
insert into Campaign
values ('Urgent Campaign demo 2', null, 'This is the description of the urgent campaign demo', '2022-10-23', null, 0,
    1, 5, 'This is the address details', 3, 'AB-O', NULL)
insert into Campaign
values ('Urgent Campaign demo 3', null, 'This is the description of the urgent campaign demo', '2022-10-23', null, 0,
    1, 5, 'This is the address details', 4, 'AB-O', NULL)
insert into Campaign
values ('Urgent Campaign demo 4', null, 'This is the description of the urgent campaign demo', '2022-10-23', null, 0,
    1, 5, 'This is the address details', 5, 'AB-O', NULL)
insert into Campaign
values ('Urgent Campaign demo 5', null, 'This is the description of the urgent campaign demo', '2022-10-23', null, 0,
    1, 5, 'This is the address details', 6, 'AB-O', NULL)
insert into Campaign
values ('Urgent Campaign demo 6', null, 'This is the description of the urgent campaign demo', '2022-10-23', null, 0,
    1, 5, 'This is the address details', 7, 'AB-O', NULL)
insert into Campaign
values ('Urgent Campaign demo 7', null, 'This is the description of the urgent campaign demo', '2022-10-23', null, 0,
    1, 5, 'This is the address details', 8, 'AB-O', NULL)
insert into Campaign
values ('Urgent Campaign demo 8', null, 'This is the description of the urgent campaign demo', '2022-10-23', null, 0,
    1, 5, 'This is the address details', 9, 'AB-O', NULL)
insert into Campaign
values ('Urgent Campaign demo 9', null, 'This is the description of the urgent campaign demo', '2022-10-23', null, 0,
    1, 5, 'This is the address details', 10, 'AB-O', NULL)


--insert into post
INSERT [dbo].[Post] ([PostingTime], [Title], [Author], [Content], [Images], [Category], [UserId], [Status]) VALUES (CAST(N'2022-10-10T23:39:33.070' AS DateTime), N'Món quà ý nghĩa dành tặng người bệnh nhân ngày Phụ nữ Việt Nam', N'Đức Thịnh – Trương Hằng', N'“Trong những ngày vợ nằm viện, tôi lại càng thấy yêu thương vợ hơn. Chính tình yêu, tình cảm vợ chồng là nguồn động lực to lớn giúp vợ chồng tôi vượt qua những khó khăn trong chặng đường chiến đấu với bệnh tật” – Đó là chia sẻ của anh Lê Văn Giang, một người chồng luôn hết lòng chăm sóc vợ đang điều trị ung thư máu tại chương trình chúc mừng Ngày Phụ nữ Việt Nam dành cho người bệnh tại Viện Huyết học – Truyền máu TW.

“Phụ nữ là để yêu thương” – Dù ở đâu, dù đang làm gì, phụ nữ cũng xứng đáng được trân trọng và luôn là một nửa yêu thương của thế giới. Với mong muốn đem đến những giây phút an vui, những món quà tinh thần và tình cảm ấm áp đến với những người phụ nữ đang điều trị, chăm sóc người thân tại Viện, Viện Huyết học – Truyền máu TW cùng các nhà hảo tâm đã tổ chức chương trình chúc mừng Ngày Phụ nữ Việt Nam dành cho người bệnh với tên gọi “Ngày An Vui”.', NULL, 1, 1, 1)

INSERT [dbo].[Post] ([PostingTime], [Title], [Author], [Content], [Images], [Category], [UserId], [Status]) VALUES (CAST(N'2022-10-10T23:39:33.070' AS DateTime), N'Món quà ý nghĩa dành tặng người bệnh nhân ngày Phụ nữ Việt Nam', N'Đức Thịnh – Trương Hằng', N'“Trong những ngày vợ nằm viện, tôi lại càng thấy yêu thương vợ hơn. Chính tình yêu, tình cảm vợ chồng là nguồn động lực to lớn giúp vợ chồng tôi vượt qua những khó khăn trong chặng đường chiến đấu với bệnh tật” – Đó là chia sẻ của anh Lê Văn Giang, một người chồng luôn hết lòng chăm sóc vợ đang điều trị ung thư máu tại chương trình chúc mừng Ngày Phụ nữ Việt Nam dành cho người bệnh tại Viện Huyết học – Truyền máu TW.

“Phụ nữ là để yêu thương” – Dù ở đâu, dù đang làm gì, phụ nữ cũng xứng đáng được trân trọng và luôn là một nửa yêu thương của thế giới. Với mong muốn đem đến những giây phút an vui, những món quà tinh thần và tình cảm ấm áp đến với những người phụ nữ đang điều trị, chăm sóc người thân tại Viện, Viện Huyết học – Truyền máu TW cùng các nhà hảo tâm đã tổ chức chương trình chúc mừng Ngày Phụ nữ Việt Nam dành cho người bệnh với tên gọi “Ngày An Vui”.', NULL, 1, 1, 1)

INSERT [dbo].[Post] ([PostingTime], [Title], [Author], [Content], [Images], [Category], [UserId], [Status]) VALUES (CAST(N'2022-10-10T23:39:33.070' AS DateTime), N'Món quà ý nghĩa dành tặng người bệnh nhân ngày Phụ nữ Việt Nam', N'Đức Thịnh – Trương Hằng', N'“Trong những ngày vợ nằm viện, tôi lại càng thấy yêu thương vợ hơn. Chính tình yêu, tình cảm vợ chồng là nguồn động lực to lớn giúp vợ chồng tôi vượt qua những khó khăn trong chặng đường chiến đấu với bệnh tật” – Đó là chia sẻ của anh Lê Văn Giang, một người chồng luôn hết lòng chăm sóc vợ đang điều trị ung thư máu tại chương trình chúc mừng Ngày Phụ nữ Việt Nam dành cho người bệnh tại Viện Huyết học – Truyền máu TW.

“Phụ nữ là để yêu thương” – Dù ở đâu, dù đang làm gì, phụ nữ cũng xứng đáng được trân trọng và luôn là một nửa yêu thương của thế giới. Với mong muốn đem đến những giây phút an vui, những món quà tinh thần và tình cảm ấm áp đến với những người phụ nữ đang điều trị, chăm sóc người thân tại Viện, Viện Huyết học – Truyền máu TW cùng các nhà hảo tâm đã tổ chức chương trình chúc mừng Ngày Phụ nữ Việt Nam dành cho người bệnh với tên gọi “Ngày An Vui”.', NULL, 1, 1, 1)

INSERT [dbo].[Post] ([PostingTime], [Title], [Author], [Content], [Images], [Category], [UserId], [Status]) VALUES (CAST(N'2022-10-10T23:39:33.070' AS DateTime), N'Món quà ý nghĩa dành tặng người bệnh nhân ngày Phụ nữ Việt Nam', N'Đức Thịnh – Trương Hằng', N'“Trong những ngày vợ nằm viện, tôi lại càng thấy yêu thương vợ hơn. Chính tình yêu, tình cảm vợ chồng là nguồn động lực to lớn giúp vợ chồng tôi vượt qua những khó khăn trong chặng đường chiến đấu với bệnh tật” – Đó là chia sẻ của anh Lê Văn Giang, một người chồng luôn hết lòng chăm sóc vợ đang điều trị ung thư máu tại chương trình chúc mừng Ngày Phụ nữ Việt Nam dành cho người bệnh tại Viện Huyết học – Truyền máu TW.

“Phụ nữ là để yêu thương” – Dù ở đâu, dù đang làm gì, phụ nữ cũng xứng đáng được trân trọng và luôn là một nửa yêu thương của thế giới. Với mong muốn đem đến những giây phút an vui, những món quà tinh thần và tình cảm ấm áp đến với những người phụ nữ đang điều trị, chăm sóc người thân tại Viện, Viện Huyết học – Truyền máu TW cùng các nhà hảo tâm đã tổ chức chương trình chúc mừng Ngày Phụ nữ Việt Nam dành cho người bệnh với tên gọi “Ngày An Vui”.', NULL, 1, 1, 1)

INSERT [dbo].[Post] ([PostingTime], [Title], [Author], [Content], [Images], [Category], [UserId], [Status]) VALUES (CAST(N'2022-10-10T23:39:33.070' AS DateTime), N'Món quà ý nghĩa dành tặng người bệnh nhân ngày Phụ nữ Việt Nam', N'Đức Thịnh – Trương Hằng', N'“Trong những ngày vợ nằm viện, tôi lại càng thấy yêu thương vợ hơn. Chính tình yêu, tình cảm vợ chồng là nguồn động lực to lớn giúp vợ chồng tôi vượt qua những khó khăn trong chặng đường chiến đấu với bệnh tật” – Đó là chia sẻ của anh Lê Văn Giang, một người chồng luôn hết lòng chăm sóc vợ đang điều trị ung thư máu tại chương trình chúc mừng Ngày Phụ nữ Việt Nam dành cho người bệnh tại Viện Huyết học – Truyền máu TW.

“Phụ nữ là để yêu thương” – Dù ở đâu, dù đang làm gì, phụ nữ cũng xứng đáng được trân trọng và luôn là một nửa yêu thương của thế giới. Với mong muốn đem đến những giây phút an vui, những món quà tinh thần và tình cảm ấm áp đến với những người phụ nữ đang điều trị, chăm sóc người thân tại Viện, Viện Huyết học – Truyền máu TW cùng các nhà hảo tâm đã tổ chức chương trình chúc mừng Ngày Phụ nữ Việt Nam dành cho người bệnh với tên gọi “Ngày An Vui”.', NULL, 1, 1, 1)

INSERT [dbo].[Post] ([PostingTime], [Title], [Author], [Content], [Images], [Category], [UserId], [Status]) VALUES (CAST(N'2022-10-10T23:39:33.070' AS DateTime), N'Món quà ý nghĩa dành tặng người bệnh nhân ngày Phụ nữ Việt Nam', NULL, N'“Trong những ngày vợ nằm viện, tôi lại càng thấy yêu thương vợ hơn. Chính tình yêu, tình cảm vợ chồng là nguồn động lực to lớn giúp vợ chồng tôi vượt qua những khó khăn trong chặng đường chiến đấu với bệnh tật” – Đó là chia sẻ của anh Lê Văn Giang, một người chồng luôn hết lòng chăm sóc vợ đang điều trị ung thư máu tại chương trình chúc mừng Ngày Phụ nữ Việt Nam dành cho người bệnh tại Viện Huyết học – Truyền máu TW.

“Phụ nữ là để yêu thương” – Dù ở đâu, dù đang làm gì, phụ nữ cũng xứng đáng được trân trọng và luôn là một nửa yêu thương của thế giới. Với mong muốn đem đến những giây phút an vui, những món quà tinh thần và tình cảm ấm áp đến với những người phụ nữ đang điều trị, chăm sóc người thân tại Viện, Viện Huyết học – Truyền máu TW cùng các nhà hảo tâm đã tổ chức chương trình chúc mừng Ngày Phụ nữ Việt Nam dành cho người bệnh với tên gọi “Ngày An Vui”.', NULL, 1, 1, 1)

INSERT [dbo].[Post] ([PostingTime], [Title], [Author], [Content], [Images], [Category], [UserId], [Status]) VALUES (CAST(N'2022-10-10T23:39:33.070' AS DateTime), N'Món quà ý nghĩa dành tặng người bệnh nhân ngày Phụ nữ Việt Nam', NULL, N'“Trong những ngày vợ nằm viện, tôi lại càng thấy yêu thương vợ hơn. Chính tình yêu, tình cảm vợ chồng là nguồn động lực to lớn giúp vợ chồng tôi vượt qua những khó khăn trong chặng đường chiến đấu với bệnh tật” – Đó là chia sẻ của anh Lê Văn Giang, một người chồng luôn hết lòng chăm sóc vợ đang điều trị ung thư máu tại chương trình chúc mừng Ngày Phụ nữ Việt Nam dành cho người bệnh tại Viện Huyết học – Truyền máu TW.

“Phụ nữ là để yêu thương” – Dù ở đâu, dù đang làm gì, phụ nữ cũng xứng đáng được trân trọng và luôn là một nửa yêu thương của thế giới. Với mong muốn đem đến những giây phút an vui, những món quà tinh thần và tình cảm ấm áp đến với những người phụ nữ đang điều trị, chăm sóc người thân tại Viện, Viện Huyết học – Truyền máu TW cùng các nhà hảo tâm đã tổ chức chương trình chúc mừng Ngày Phụ nữ Việt Nam dành cho người bệnh với tên gọi “Ngày An Vui”.', NULL, 1, 1, 1)

INSERT [dbo].[Post] ([PostingTime], [Title], [Author], [Content], [Images], [Category], [UserId], [Status]) VALUES (CAST(N'2022-10-10T23:39:33.070' AS DateTime), N'Món quà ý nghĩa dành tặng người bệnh nhân ngày Phụ nữ Việt Nam', NULL, N'“Trong những ngày vợ nằm viện, tôi lại càng thấy yêu thương vợ hơn. Chính tình yêu, tình cảm vợ chồng là nguồn động lực to lớn giúp vợ chồng tôi vượt qua những khó khăn trong chặng đường chiến đấu với bệnh tật” – Đó là chia sẻ của anh Lê Văn Giang, một người chồng luôn hết lòng chăm sóc vợ đang điều trị ung thư máu tại chương trình chúc mừng Ngày Phụ nữ Việt Nam dành cho người bệnh tại Viện Huyết học – Truyền máu TW.

“Phụ nữ là để yêu thương” – Dù ở đâu, dù đang làm gì, phụ nữ cũng xứng đáng được trân trọng và luôn là một nửa yêu thương của thế giới. Với mong muốn đem đến những giây phút an vui, những món quà tinh thần và tình cảm ấm áp đến với những người phụ nữ đang điều trị, chăm sóc người thân tại Viện, Viện Huyết học – Truyền máu TW cùng các nhà hảo tâm đã tổ chức chương trình chúc mừng Ngày Phụ nữ Việt Nam dành cho người bệnh với tên gọi “Ngày An Vui”.', NULL, 1, 1, 1)

INSERT [dbo].[Post] ([PostingTime], [Title], [Author], [Content], [Images], [Category], [UserId], [Status]) VALUES (CAST(N'2022-10-10T23:39:33.070' AS DateTime), N'Món quà ý nghĩa dành tặng người bệnh nhân ngày Phụ nữ Việt Nam', NULL, N'“Trong những ngày vợ nằm viện, tôi lại càng thấy yêu thương vợ hơn. Chính tình yêu, tình cảm vợ chồng là nguồn động lực to lớn giúp vợ chồng tôi vượt qua những khó khăn trong chặng đường chiến đấu với bệnh tật” – Đó là chia sẻ của anh Lê Văn Giang, một người chồng luôn hết lòng chăm sóc vợ đang điều trị ung thư máu tại chương trình chúc mừng Ngày Phụ nữ Việt Nam dành cho người bệnh tại Viện Huyết học – Truyền máu TW.

“Phụ nữ là để yêu thương” – Dù ở đâu, dù đang làm gì, phụ nữ cũng xứng đáng được trân trọng và luôn là một nửa yêu thương của thế giới. Với mong muốn đem đến những giây phút an vui, những món quà tinh thần và tình cảm ấm áp đến với những người phụ nữ đang điều trị, chăm sóc người thân tại Viện, Viện Huyết học – Truyền máu TW cùng các nhà hảo tâm đã tổ chức chương trình chúc mừng Ngày Phụ nữ Việt Nam dành cho người bệnh với tên gọi “Ngày An Vui”.', NULL, 1, 1, 1)

INSERT [dbo].[Post] ([PostingTime], [Title], [Author], [Content], [Images], [Category], [UserId], [Status]) VALUES (CAST(N'2022-10-10T23:39:33.070' AS DateTime), N'Món quà ý nghĩa dành tặng người bệnh nhân ngày Phụ nữ Việt Nam', NULL, N'“Trong những ngày vợ nằm viện, tôi lại càng thấy yêu thương vợ hơn. Chính tình yêu, tình cảm vợ chồng là nguồn động lực to lớn giúp vợ chồng tôi vượt qua những khó khăn trong chặng đường chiến đấu với bệnh tật” – Đó là chia sẻ của anh Lê Văn Giang, một người chồng luôn hết lòng chăm sóc vợ đang điều trị ung thư máu tại chương trình chúc mừng Ngày Phụ nữ Việt Nam dành cho người bệnh tại Viện Huyết học – Truyền máu TW.

“Phụ nữ là để yêu thương” – Dù ở đâu, dù đang làm gì, phụ nữ cũng xứng đáng được trân trọng và luôn là một nửa yêu thương của thế giới. Với mong muốn đem đến những giây phút an vui, những món quà tinh thần và tình cảm ấm áp đến với những người phụ nữ đang điều trị, chăm sóc người thân tại Viện, Viện Huyết học – Truyền máu TW cùng các nhà hảo tâm đã tổ chức chương trình chúc mừng Ngày Phụ nữ Việt Nam dành cho người bệnh với tên gọi “Ngày An Vui”.', NULL, 1, 1, 1)