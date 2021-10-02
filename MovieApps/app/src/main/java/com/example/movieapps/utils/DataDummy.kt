package com.example.movieapps.utils

import com.example.movieapps.data.MovieEntity
import com.example.movieapps.data.TvEntity

object DataDummy {

    fun generateDummyMovies(): List<MovieEntity> {
        val movies = ArrayList<MovieEntity>()

        movies.add(
            MovieEntity(
                "1",
                "Free Guy",
                "A bank teller called Guy realizes he is a background character in an open world video game called Free City that will soon go offline.",
                "2021-08-11",
                7586.342,
                8,
                100,
                "https://www.themoviedb.org/t/p/original/xmbU4JTUm8rsdtn7Y3Fcm30GpeT.jpg"
            )
        )

        movies.add(
            MovieEntity(
                "2",
                "Snake Eyes: G.I. Joe Origins",
                "After saving the life of their heir apparent, tenacious loner Snake Eyes is welcomed into an ancient Japanese clan called the Arashikage where he is taught the ways of the ninja warrior. But, when secrets from his past are revealed, Snake Eyes' honor and allegiance will be tested – even if that means losing the trust of those closest to him.",
                "2021-07-22",
                4586.342,
                18,
                120,
                "https://www.themoviedb.org/t/p/original/uIXF0sQGXOxQhbaEaKOi2VYlIL0.jpg"
            )
        )

        movies.add(
            MovieEntity(
                "3",
                "The Stronghold",
                "A police brigade working in the dangerous northern neighborhoods of Marseille, where the level of crime is higher than anywhere else in France.",
                "2021-06-18",
                2586.342,
                18,
                120,
                "https://www.themoviedb.org/t/p/original/nLanxl7Xhfbd5s8FxPy8jWZw4rv.jpg"
            )
        )

        movies.add(
            MovieEntity(
                "4",
                "Shang-Chi and the Legend of the Ten Rings",
                "Shang-Chi must confront the past he thought he left behind when he is drawn into the web of the mysterious Ten Rings organization.",
                "2021-07-18",
                5586.342,
                28,
                150,
                "https://www.themoviedb.org/t/p/original/xeItgLK9qcafxbd8kYgv7XnMEog.jpg"
            )
        )

        movies.add(
            MovieEntity(
                "5",
                "Old",
                "A group of families on a tropical holiday discover that the secluded beach where they are staying is somehow causing them to age rapidly – reducing their entire lives into a single day.",
                "2021-07-21",
                5586.342,
                28,
                150,
                "https://www.themoviedb.org/t/p/original/vclShucpUmPhdAOmKgf3B3Z4POD.jpg"
            )
        )

        movies.add(
            MovieEntity(
                "6",
                "The Flash",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \\\"meta-human\\\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                "2021-01-01",
                2586.342,
                1238,
                8472,
                "https://www.themoviedb.org/t/p/original/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg"
            )
        )

        movies.add(
            MovieEntity(
                "7",
                "The Last Warrior: Root of Evil",
                "Peace and tranquility have set in Belogorie. The evil was defeated and Ivan is now enjoying his well-deserved fame. He is surrounded by his family, friends and small wonders from the modern world that help him lead a comfortable life. Luckily, he has his Magic Sword to cut a gap between the worlds to get some supplies quite regularly. But when an ancient evil rises and the existence of the magic world is put to danger, Ivan has to team up with his old friends and his new rivals. They will set out on a long journey beyond the known world to find a way to defeat the enemies and to return peace to Belogorie.",
                "2021-01-01",
                6586.342,
                128,
                250,
                "https://www.themoviedb.org/t/p/original/5VJSIAhSn4qUsg5nOj4MhQhF5wQ.jpg"
            )
        )

        movies.add(
            MovieEntity(
                "8",
                "Catch the Bullet",
                "U.S. marshal Britt MacMasters returns from a mission to find his father wounded and his son kidnapped by the outlaw Jed Blake. Hot on their trail, Britt forms a posse with a gunslinging deputy and a stoic Pawnee tracker. But Jed and Britt tread dangerously close to the Red Desert’s Sioux territory.",
                "2021-09-10",
                3586.342,
                228,
                350,
                "https://www.themoviedb.org/t/p/original/qKxrBZ8Ts4KHZKp7BT6GAVMLFO2.jpg"
            )
        )

        movies.add(
            MovieEntity(
                "9",
                "The Suicide Squad",
                "Supervillains Harley Quinn, Bloodsport, Peacemaker and a collection of nutty cons at Belle Reve prison join the super-secret, super-shady Task Force X as they are dropped off at the remote, enemy-infused island of Corto Maltese.",
                "2021-07-28",
                1259.968,
                228,
                650,
                "https://www.themoviedb.org/t/p/original/kb4s0ML0iVZlG6wAKbbs9NAm6X.jpg"
            )
        )

        movies.add(
            MovieEntity(
                "10",
                "Deathstroke: Knights & Dragons - The Movie",
                "The assassin Deathstroke tries to save his family from the wrath of H.I.V.E. and the murderous Jackal.",
                "2021-06-28",
                4259.968,
                228,
                650,
                "https://www.themoviedb.org/t/p/original/vFIHbiy55smzi50RmF8LQjmpGcx.jpg"
            )
        )

        return movies
    }

    fun generateTvs(): List<TvEntity> {
        val tv = ArrayList<TvEntity>()

        tv.add(
            TvEntity(
                "1",
                "Squid Game",
                "Hundreds of cash-strapped players accept a strange invitation to compete in children's games. Inside, a tempting prize awaits — with deadly high stakes.",
                "2021-09-17",
                "https://www.themoviedb.org/t/p/original/dDlEmu3EZ0Pgg93K2SVNLCjCSvE.jpg",
                3513.163,
                8.0,
                90
            )
        )

        tv.add(
            TvEntity(
                "2",
                "Sex Education",
                "Inexperienced Otis channels his sex therapist mom when he teams up with rebellious Maeve to set up an underground sex therapy clinic at school.",
                "2020-12-09",
                "https://www.themoviedb.org/t/p/original/8j12tohv1NBZNmpU93f47sAKBbw.jpg",
                2513.163,
                8.0,
                190
            )
        )

        tv.add(
            TvEntity(
                "3",
                "El Juego de las Llaves",
                "A casual encounter between Adriana and Sergio, two former high school friends, triggers the beginning of the game of the keys where 4 couples generate new sexual and emotional combinations in a dangerous and lustful game.",
                "2020-08-16",
                "https://www.themoviedb.org/t/p/original/2cMy9qphoOLJjYJJJQBBul0d94q.jpg",
                4513.163,
                6.0,
                90
            )
        )


        tv.add(
            TvEntity(
                "4",
                "What If...?",
                "Taking inspiration from the comic books of the same name, each episode explores a pivotal moment from the Marvel Cinematic Universe and turns it on its head, leading the audience into uncharted territory.",
                "2020-08-11",
                "https://www.themoviedb.org/t/p/original/lztz5XBMG1x6Y5ubz7CxfPFsAcW.jpg",
                2513.163,
                8.0,
                190
            )
        )


        tv.add(
            TvEntity(
                "5",
                "Lucifer",
                "Bored and unhappy as the Lord of Hell, Lucifer Morningstar abandoned his throne and retired to Los Angeles, where he has teamed up with LAPD detective Chloe Decker to take down criminals. But the longer he's away from the underworld, the greater the threat that the worst of humanity could escape.",
                "2016-06-11",
                "https://www.themoviedb.org/t/p/original/ekZobS8isE6mA53RAiGDG93hBxL.jpg",
                1471.163,
                6.0,
                290
            )
        )

        tv.add(
            TvEntity(
                "6",
                "Hello Procurator",
                "Jiang Wen Jing's dream is to become a prosecutor. She successfully gets accepted into the public prosecutor's office and under the guidance of fellow colleagues Yin Chuan and Yu Kai Yin, takes on many difficult cases that help her grow into a successful prosecutor.",
                "2017-09-08",
                "https://www.themoviedb.org/t/p/original/ldV2Ej7T0E37zq4rtVVeGB9V6iJ.jpg",
                471.163,
                6.0,
                390
            )
        )

        tv.add(
            TvEntity(
                "7",
                "Money Heist",
                "To carry out the biggest heist in history, a mysterious man called The Professor recruits a band of eight robbers who have a single characteristic: none of them has anything to lose. Five months of seclusion - memorizing every step, every detail, every probability - culminate in eleven days locked up in the National Coinage and Stamp Factory of Spain, surrounded by police forces and with dozens of hostages in their power, to find out whether their suicide wager will lead to everything or nothing.",
                "2017-05-08",
                "https://www.themoviedb.org/t/p/original/reEMJA1uzscCbkpeRJeTT2bjqUp.jpg",
                1471.163,
                8.2,
                145000
            )
        )

        tv.add(
            TvEntity(
                "8",
                "Riverdale",
                "Set in the present, the series offers a bold, subversive take on Archie, Betty, Veronica and their friends, exploring the surreality of small-town life, the darkness and weirdness bubbling beneath Riverdale’s wholesome facade.",
                "2017-01-26",
                "https://www.themoviedb.org/t/p/original/wRbjVBdDo5qHAEOVYoMWpM58FSA.jpg",
                1471.163,
                8.2,
                125000
            )
        )

        tv.add(
            TvEntity(
                "9",
                "The Walking Dead",
                "Sheriff's deputy Rick Grimes awakens from a coma to find a post-apocalyptic world dominated by flesh-eating zombies. He sets out to find his family and encounters many other survivors along the way.",
                "2018-10-26",
                "https://www.themoviedb.org/t/p/original/w21lgYIi9GeUH5dO8l3B9ARZbCB.jpg",
                471.163,
                7.2,
                25000
            )
        )

        tv.add(
            TvEntity(
                "10",
                "Big Brother",
                "Portuguese version of the reality competition which follows a group of HouseGuests living together 24 hours a day in the \"Big Brother\" house, isolated from the outside world but under constant surveillance with no privacy.",
                "2019-10-26",
                "https://www.themoviedb.org/t/p/original/9Gkcvdz89Q7w0glDDga1c8m5vaW.jpg",
                473.163,
                7.2,
                15500
            )
        )

        return tv
    }

}