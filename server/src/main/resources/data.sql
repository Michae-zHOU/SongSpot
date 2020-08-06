CREATE SCHEMA IF NOT EXISTS public;

INSERT INTO public.curators (id, created_at, updated_at, name, avatar, bio, email, password, user_type, website)
VALUES (1, '2020-08-06 00:03:07.974000', '2020-08-06 00:03:07.974000', 'Michael_Zhou', E'\\x00000000', 'WeChatBanner', 'mz@gmail.com', '12345678', 1, 'www.wechatbanner.com');

INSERT INTO public.curators (id, created_at, updated_at, name, avatar, bio, email, password, user_type, website)
VALUES (2, '2020-08-06 00:03:07.974000', '2020-08-06 00:03:07.974000', 'Diswather', E'\\x00000000', 'DissWasher For Life', 'dwasher@gmail.com', '12345678', 1, 'www.diswasher.com');

INSERT INTO public.artists (id, created_at, updated_at, name, avatar, bio, email, password, user_type, website, followers_count, songs_count)
VALUES (3, '2020-08-06 00:11:48.958000', '2020-08-06 00:11:48.958000', 'Phase_Hooker', E'\\x01010101', 'SoundClouder', 'phasehooker@gmail.com', '87654321', 0, 'www.phasehooker.com', 0, 0);

INSERT INTO public.demo_track (id, created_at, updated_at, artist, data, filename, file_type)
VALUES (1, '2020-08-06 00:06:54.309000', '2020-08-06 00:06:54.309000', 'Phase_Hooker', 158131, 'MatrixSolver.mp3', 'wav');

INSERT INTO public.demo_track_curator (curator_id, demo_track_id, viewed)
VALUES (1, 1, false);
