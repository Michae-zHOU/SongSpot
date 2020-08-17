CREATE SCHEMA IF NOT EXISTS public;

INSERT INTO public.curators (id, created_at, updated_at, name, avatar, bio, email, password, user_type, website)
VALUES (1, '2020-08-06 00:03:07.974000', '2020-08-06 00:03:07.974000', 'Michael_Zhou', E'\\x00000000', 'WeChatBanner', 'mz@gmail.com', '12345678', 1, 'www.wechatbanner.com');

INSERT INTO public.curators (id, created_at, updated_at, name, avatar, bio, email, password, user_type, website)
VALUES (2, '2020-08-06 00:03:07.974000', '2020-08-06 00:03:07.974000', 'Diswather', E'\\x00000000', 'DissWasher For Life', 'dwasher@gmail.com', '12345678', 1, 'www.diswasher.com');

INSERT INTO public.artists (id, created_at, updated_at, name, avatar, bio, email, password, user_type, website, followers_count, songs_count)
VALUES (3, '2020-08-16 20:48:56.000000', '2020-08-16 20:48:52.000000', 'Phase_Hooker', null, 'SoundClouder', 'www.phasehooker.com', 'test', 0, 'www.phasehooker.com', 0, 1);

INSERT INTO public.demo_tracks (id, created_at, updated_at, artist, data, filename, file_type)
VALUES (1, '2020-08-06 00:06:54.309000', '2020-08-06 00:06:54.309000', 'Phase_Hooker', 158131, 'MatrixSolver.mp3', 'wav');

INSERT INTO public.demo_track_curators (curator_id, demo_track_id, viewed)
VALUES (1, 1, false);