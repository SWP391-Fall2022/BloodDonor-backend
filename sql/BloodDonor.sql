USE [master]
GO
DROP DATABASE IF EXISTS [BloodDonorProject]
GO
/****** Object:  Database [BloodDonorProject]    Script Date: 10/10/2022 5:04:28 PM ******/
CREATE DATABASE [BloodDonorProject]
GO
USE [BloodDonorProject]
GO
/****** Object:  Table [dbo].[Campaign]    Script Date: 10/10/2022 5:04:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Campaign](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](255) NOT NULL,
	[Images] [varchar](1000) NULL,
	[Description] [ntext] NULL,
	[StartDate] [date] NOT NULL,
	[EndDate] [date] NULL,
	[Emergency] [bit] NOT NULL,
	[Status] [bit] NOT NULL,
	[DistrictId] [int] NOT NULL,
	[AddressDetails] [ntext] NULL,
	[OrganizationId] [int] NOT NULL,
	[BloodTypes] [varchar](50) NOT NULL,
 CONSTRAINT [PK__Campaign__3214EC078FC4A70B] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[District]    Script Date: 10/10/2022 5:04:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[District](
	[Id] [int] NOT NULL,
	[Name] [nvarchar](255) NOT NULL,
	[Prefix] [nvarchar](100) NULL,
	[ProvinceId] [int] NOT NULL,
 CONSTRAINT [PK__District__3214EC07CE8714C2] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DonateRecord]    Script Date: 10/10/2022 5:04:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DonateRecord](
	[DonorId] [int] NOT NULL,
	[CampaignId] [int] NOT NULL,
	[RegisteredDate] [date] NOT NULL,
	[Details] [ntext] NULL,
	[Status] [bit] NOT NULL,
	[BloodType] [varchar](50) NOT NULL,
	[Amount] [int] NULL,
	[Weight] [float] NULL,
 CONSTRAINT [PK_DonateRecord] PRIMARY KEY CLUSTERED 
(
	[DonorId] ASC,
	[CampaignId] ASC,
	[RegisteredDate] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DonateRegistration]    Script Date: 10/10/2022 5:04:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DonateRegistration](
	[DonorId] [int] NOT NULL,
	[CampaignId] [int] NOT NULL,
	[RegisteredDate] [date] NOT NULL,
	[Status] [varchar](100) NOT NULL,
	[Period] [varchar](20) NOT NULL,
	[Code] [varchar](20) NOT NULL,
 CONSTRAINT [PK_DonateRegistration] PRIMARY KEY CLUSTERED 
(
	[DonorId] ASC,
	[CampaignId] ASC,
	[RegisteredDate] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Donor]    Script Date: 10/10/2022 5:04:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Donor](
	[UserId] [int] NOT NULL,
	[Name] [nvarchar](255) NOT NULL,
	[Birthday] [date] NOT NULL,
	[Sex] [varchar](10) NOT NULL,
	[IdentityNum] [varchar](50) NOT NULL,
	[Avatar] [varchar](1000) NULL,
	[BloodType] [varchar](255) NULL,
	[Anamnesis] [ntext] NULL,
 CONSTRAINT [PK__Donor__1788CC4CB6C819D6] PRIMARY KEY CLUSTERED 
(
	[UserId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[EarnedReward]    Script Date: 10/10/2022 5:04:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[EarnedReward](
	[DonorId] [int] NOT NULL,
	[RewardId] [int] NOT NULL,
	[ReceiveDate] [date] NOT NULL,
 CONSTRAINT [PK_EarnedReward] PRIMARY KEY CLUSTERED 
(
	[DonorId] ASC,
	[RewardId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[LikeRecord]    Script Date: 10/10/2022 5:04:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LikeRecord](
	[DonorId] [int] NOT NULL,
	[CampaignId] [int] NOT NULL,
 CONSTRAINT [PK_LikeRecord] PRIMARY KEY CLUSTERED 
(
	[DonorId] ASC,
	[CampaignId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Message]    Script Date: 10/10/2022 5:04:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Message](
	[MessageId] [int] IDENTITY(1,1) NOT NULL,
	[SenderId] [int] NOT NULL,
	[ReceiverId] [int] NOT NULL,
	[SendingTime] [datetime] NOT NULL,
	[Content] [ntext] NOT NULL,
 CONSTRAINT [PK__Message__C87C0C9CECF3B0A5] PRIMARY KEY CLUSTERED 
(
	[MessageId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Organization]    Script Date: 10/10/2022 5:04:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Organization](
	[UserId] [int] NOT NULL,
	[Name] [nvarchar](255) NOT NULL,
	[TaxCode] [varchar](50) NOT NULL,
	[Approve] [varchar](20) NOT NULL,
	[Avatar] [varchar](1000) NULL,
	[Website] [varchar](255) NULL,
	[Introduction] [ntext] NULL,
 CONSTRAINT [PK__Organiza__1788CC4CDCE654A9] PRIMARY KEY CLUSTERED 
(
	[UserId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Post]    Script Date: 10/10/2022 5:04:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Post](
	[PostId] [int] IDENTITY(1,1) NOT NULL,
	[PostingTime] [datetime] NOT NULL,
	[Title] [ntext] NOT NULL,
	[Author] [nvarchar](255) NULL,
	[Content] [ntext] NOT NULL,
	[Images] [varchar](1000) NULL,
	[Category] [int] NULL,
	[UserId] [int] NOT NULL,
	[Status] [bit] NOT NULL,
 CONSTRAINT [PK__Post__AA12601883A6D477] PRIMARY KEY CLUSTERED 
(
	[PostId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Province]    Script Date: 10/10/2022 5:04:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Province](
	[Id] [int] NOT NULL,
	[Name] [nvarchar](255) NOT NULL,
	[Code] [varchar](100) NOT NULL,
 CONSTRAINT [PK__Province__3214EC07A258D127] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Question]    Script Date: 10/10/2022 5:04:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Question](
	[QuestionId] [int] IDENTITY(1,1) NOT NULL,
	[DonorId] [int] NOT NULL,
	[CampaignId] [int] NOT NULL,
	[AskTime] [datetime] NOT NULL,
	[Question] [ntext] NOT NULL,
	[Answer] [ntext] NULL,
	[Status] [bit] NOT NULL,
 CONSTRAINT [PK__Question__0DC06FAC17537ED7] PRIMARY KEY CLUSTERED 
(
	[QuestionId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Reward]    Script Date: 10/10/2022 5:04:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Reward](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[ExpiredDate] [date] NOT NULL,
	[Level] [int] NOT NULL,
	[Sponsor] [nvarchar](255) NOT NULL,
	[Code] [varchar](255) NOT NULL,
	[Status] [bit] NOT NULL,
	[Details] [ntext] NULL,
	[Amount] [int] NOT NULL,
 CONSTRAINT [PK__Reward__3214EC07D05615D5] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[User]    Script Date: 10/10/2022 5:04:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[User](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Username] [varchar](255) NOT NULL,
	[Password] [varchar](255) NOT NULL,
	[Phone] [varchar](20) NOT NULL,
	[Email] [varchar](255) NOT NULL,
	[DistrictId] [int] NOT NULL,
	[AddressDetails] [ntext] NULL,
	[Role] [varchar](20) NOT NULL,
	[Status] [bit] NOT NULL,
	[Enabled] [bit] NOT NULL,
 CONSTRAINT [PK__Users__3214EC072A79D602] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[VerificationCode]    Script Date: 10/10/2022 5:04:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[VerificationCode](
	[UserId] [int] NOT NULL,
	[Code] [int] NOT NULL,
	[createdAt] [datetime] NOT NULL,
	[expiresAt] [datetime] NOT NULL,
	[confirmed] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[UserId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Campaign] ADD  CONSTRAINT [DF__Campaign__Images__3E52440B]  DEFAULT (NULL) FOR [Images]
GO
ALTER TABLE [dbo].[Campaign] ADD  CONSTRAINT [DF__Campaign__Descri__3F466844]  DEFAULT (NULL) FOR [Description]
GO
ALTER TABLE [dbo].[Campaign] ADD  CONSTRAINT [DF_Campaign_Status]  DEFAULT ((1)) FOR [Status]
GO
ALTER TABLE [dbo].[Campaign] ADD  CONSTRAINT [DF__Campaign__Addres__412EB0B6]  DEFAULT (NULL) FOR [AddressDetails]
GO
ALTER TABLE [dbo].[Campaign] ADD  CONSTRAINT [DF_Campaign_BloodTypes]  DEFAULT ('A-B-AB-O') FOR [BloodTypes]
GO
ALTER TABLE [dbo].[District] ADD  CONSTRAINT [DF__District__Name__286302EC]  DEFAULT (NULL) FOR [Name]
GO
ALTER TABLE [dbo].[District] ADD  CONSTRAINT [DF__District__Prefix__29572725]  DEFAULT (NULL) FOR [Prefix]
GO
ALTER TABLE [dbo].[Donor] ADD  CONSTRAINT [DF__Donor__Avatar__33D4B598]  DEFAULT (NULL) FOR [Avatar]
GO
ALTER TABLE [dbo].[Donor] ADD  CONSTRAINT [DF__Donor__BloodType__34C8D9D1]  DEFAULT (NULL) FOR [BloodType]
GO
ALTER TABLE [dbo].[Donor] ADD  CONSTRAINT [DF__Donor__Anamnesis__35BCFE0A]  DEFAULT (NULL) FOR [Anamnesis]
GO
ALTER TABLE [dbo].[Organization] ADD  CONSTRAINT [DF__Organizat__Avata__398D8EEE]  DEFAULT (NULL) FOR [Avatar]
GO
ALTER TABLE [dbo].[Organization] ADD  CONSTRAINT [DF__Organizat__Websi__3A81B327]  DEFAULT (NULL) FOR [Website]
GO
ALTER TABLE [dbo].[Organization] ADD  CONSTRAINT [DF__Organizat__Intro__3B75D760]  DEFAULT (NULL) FOR [Introduction]
GO
ALTER TABLE [dbo].[Post] ADD  CONSTRAINT [DF__Post__Images__5FB337D6]  DEFAULT (NULL) FOR [Images]
GO
ALTER TABLE [dbo].[Post] ADD  CONSTRAINT [DF_Post_Status]  DEFAULT ((1)) FOR [Status]
GO
ALTER TABLE [dbo].[Province] ADD  CONSTRAINT [DF__Province__Name__24927208]  DEFAULT (NULL) FOR [Name]
GO
ALTER TABLE [dbo].[Province] ADD  CONSTRAINT [DF__Province__Code__25869641]  DEFAULT (NULL) FOR [Code]
GO
ALTER TABLE [dbo].[Question] ADD  CONSTRAINT [DF__Questions__Answe__4E88ABD4]  DEFAULT (NULL) FOR [Answer]
GO
ALTER TABLE [dbo].[Question] ADD  CONSTRAINT [DF__Questions__Statu__4F7CD00D]  DEFAULT ((0)) FOR [Status]
GO
ALTER TABLE [dbo].[Reward] ADD  CONSTRAINT [DF_Reward_Status]  DEFAULT ((1)) FOR [Status]
GO
ALTER TABLE [dbo].[User] ADD  CONSTRAINT [DF__Users__Phone__2D27B809]  DEFAULT (NULL) FOR [Phone]
GO
ALTER TABLE [dbo].[User] ADD  CONSTRAINT [DF__Users__Email__2E1BDC42]  DEFAULT (NULL) FOR [Email]
GO
ALTER TABLE [dbo].[User] ADD  CONSTRAINT [DF__Users__AddressDe__300424B4]  DEFAULT (NULL) FOR [AddressDetails]
GO
ALTER TABLE [dbo].[User] ADD  CONSTRAINT [DF_Users_Status]  DEFAULT ((1)) FOR [Status]
GO
ALTER TABLE [dbo].[User] ADD  CONSTRAINT [DF_Users_Enabled]  DEFAULT ((0)) FOR [Enabled]
GO
ALTER TABLE [dbo].[VerificationCode] ADD  DEFAULT ((0)) FOR [confirmed]
GO
ALTER TABLE [dbo].[Campaign]  WITH CHECK ADD  CONSTRAINT [FK__Campaign__Distri__403A8C7D] FOREIGN KEY([DistrictId])
REFERENCES [dbo].[District] ([Id])
GO
ALTER TABLE [dbo].[Campaign] CHECK CONSTRAINT [FK__Campaign__Distri__403A8C7D]
GO
ALTER TABLE [dbo].[Campaign]  WITH CHECK ADD  CONSTRAINT [FK__Campaign__Organi__4222D4EF] FOREIGN KEY([OrganizationId])
REFERENCES [dbo].[Organization] ([UserId])
GO
ALTER TABLE [dbo].[Campaign] CHECK CONSTRAINT [FK__Campaign__Organi__4222D4EF]
GO
ALTER TABLE [dbo].[District]  WITH CHECK ADD  CONSTRAINT [FK__District__provin__2A4B4B5E] FOREIGN KEY([ProvinceId])
REFERENCES [dbo].[Province] ([Id])
GO
ALTER TABLE [dbo].[District] CHECK CONSTRAINT [FK__District__provin__2A4B4B5E]
GO
ALTER TABLE [dbo].[DonateRecord]  WITH CHECK ADD  CONSTRAINT [FK__DonateRec__Campa__534D60F1] FOREIGN KEY([CampaignId])
REFERENCES [dbo].[Campaign] ([Id])
GO
ALTER TABLE [dbo].[DonateRecord] CHECK CONSTRAINT [FK__DonateRec__Campa__534D60F1]
GO
ALTER TABLE [dbo].[DonateRecord]  WITH CHECK ADD  CONSTRAINT [FK__DonateRec__Donor__52593CB8] FOREIGN KEY([DonorId])
REFERENCES [dbo].[Donor] ([UserId])
GO
ALTER TABLE [dbo].[DonateRecord] CHECK CONSTRAINT [FK__DonateRec__Donor__52593CB8]
GO
ALTER TABLE [dbo].[DonateRegistration]  WITH CHECK ADD  CONSTRAINT [FK__DonateReg__Campa__45F365D3] FOREIGN KEY([CampaignId])
REFERENCES [dbo].[Campaign] ([Id])
GO
ALTER TABLE [dbo].[DonateRegistration] CHECK CONSTRAINT [FK__DonateReg__Campa__45F365D3]
GO
ALTER TABLE [dbo].[DonateRegistration]  WITH CHECK ADD  CONSTRAINT [FK__DonateReg__Donor__44FF419A] FOREIGN KEY([DonorId])
REFERENCES [dbo].[Donor] ([UserId])
GO
ALTER TABLE [dbo].[DonateRegistration] CHECK CONSTRAINT [FK__DonateReg__Donor__44FF419A]
GO
ALTER TABLE [dbo].[Donor]  WITH CHECK ADD  CONSTRAINT [FK__Donor__UserId__32E0915F] FOREIGN KEY([UserId])
REFERENCES [dbo].[User] ([Id])
GO
ALTER TABLE [dbo].[Donor] CHECK CONSTRAINT [FK__Donor__UserId__32E0915F]
GO
ALTER TABLE [dbo].[EarnedReward]  WITH CHECK ADD  CONSTRAINT [FK__EarnedRew__Donor__5812160E] FOREIGN KEY([DonorId])
REFERENCES [dbo].[Donor] ([UserId])
GO
ALTER TABLE [dbo].[EarnedReward] CHECK CONSTRAINT [FK__EarnedRew__Donor__5812160E]
GO
ALTER TABLE [dbo].[EarnedReward]  WITH CHECK ADD  CONSTRAINT [FK__EarnedRew__Rewar__59063A47] FOREIGN KEY([RewardId])
REFERENCES [dbo].[Reward] ([Id])
GO
ALTER TABLE [dbo].[EarnedReward] CHECK CONSTRAINT [FK__EarnedRew__Rewar__59063A47]
GO
ALTER TABLE [dbo].[LikeRecord]  WITH CHECK ADD  CONSTRAINT [FK__LikeRecor__Campa__49C3F6B7] FOREIGN KEY([CampaignId])
REFERENCES [dbo].[Campaign] ([Id])
GO
ALTER TABLE [dbo].[LikeRecord] CHECK CONSTRAINT [FK__LikeRecor__Campa__49C3F6B7]
GO
ALTER TABLE [dbo].[LikeRecord]  WITH CHECK ADD  CONSTRAINT [FK__LikeRecor__Donor__48CFD27E] FOREIGN KEY([DonorId])
REFERENCES [dbo].[Donor] ([UserId])
GO
ALTER TABLE [dbo].[LikeRecord] CHECK CONSTRAINT [FK__LikeRecor__Donor__48CFD27E]
GO
ALTER TABLE [dbo].[Message]  WITH CHECK ADD  CONSTRAINT [FK__Message__Receive__5CD6CB2B] FOREIGN KEY([ReceiverId])
REFERENCES [dbo].[User] ([Id])
GO
ALTER TABLE [dbo].[Message] CHECK CONSTRAINT [FK__Message__Receive__5CD6CB2B]
GO
ALTER TABLE [dbo].[Message]  WITH CHECK ADD  CONSTRAINT [FK__Message__SenderI__5BE2A6F2] FOREIGN KEY([SenderId])
REFERENCES [dbo].[User] ([Id])
GO
ALTER TABLE [dbo].[Message] CHECK CONSTRAINT [FK__Message__SenderI__5BE2A6F2]
GO
ALTER TABLE [dbo].[Organization]  WITH CHECK ADD  CONSTRAINT [FK__Organizat__UserI__38996AB5] FOREIGN KEY([UserId])
REFERENCES [dbo].[User] ([Id])
GO
ALTER TABLE [dbo].[Organization] CHECK CONSTRAINT [FK__Organizat__UserI__38996AB5]
GO
ALTER TABLE [dbo].[Post]  WITH CHECK ADD  CONSTRAINT [FK__Post__UserId__60A75C0F] FOREIGN KEY([UserId])
REFERENCES [dbo].[User] ([Id])
GO
ALTER TABLE [dbo].[Post] CHECK CONSTRAINT [FK__Post__UserId__60A75C0F]
GO
ALTER TABLE [dbo].[Question]  WITH CHECK ADD  CONSTRAINT [FK__Questions__Campa__4D94879B] FOREIGN KEY([CampaignId])
REFERENCES [dbo].[Campaign] ([Id])
GO
ALTER TABLE [dbo].[Question] CHECK CONSTRAINT [FK__Questions__Campa__4D94879B]
GO
ALTER TABLE [dbo].[Question]  WITH CHECK ADD  CONSTRAINT [FK__Questions__Donor__4CA06362] FOREIGN KEY([DonorId])
REFERENCES [dbo].[Donor] ([UserId])
GO
ALTER TABLE [dbo].[Question] CHECK CONSTRAINT [FK__Questions__Donor__4CA06362]
GO
ALTER TABLE [dbo].[User]  WITH CHECK ADD  CONSTRAINT [FK__Users__DistrictI__2F10007B] FOREIGN KEY([DistrictId])
REFERENCES [dbo].[District] ([Id])
GO
ALTER TABLE [dbo].[User] CHECK CONSTRAINT [FK__Users__DistrictI__2F10007B]
GO
ALTER TABLE [dbo].[VerificationCode]  WITH CHECK ADD FOREIGN KEY([UserId])
REFERENCES [dbo].[User] ([Id])
GO
USE [master]
GO
ALTER DATABASE [BloodDonorProject] SET  READ_WRITE 
GO