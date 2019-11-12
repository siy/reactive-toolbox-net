package org.reactivetoolbox.codec.json;

import org.junit.jupiter.api.Test;
import org.reactivetoolbox.core.lang.Option;

import static org.junit.jupiter.api.Assertions.*;

class ScannerTest {

    @Test
    void testParsing() {
        final var scanner = Scanner.scanner(jsonData);
        boolean repeat = true;

        while (repeat) {
            final var next = scanner.next();

            repeat = next.or(Token.EOF)
                .whenPresent(System.out::println)
                .filter(v -> v.type() == Token.TokenType.EOF)
                .map((v) -> true, (v) -> false);
        }
    }

    private final String jsonData0 = "null";

    private final String jsonData = "[\n" +
                                    "  {\n" +
                                    "    \"_id\": \"5dcad39743efcd36ae0ee86a\",\n" +
                                    "    \"index\": 0,\n" +
                                    "    \"guid\": \"9d6d5595-1521-48b0-b79e-65f689c3f6c6\",\n" +
                                    "    \"isActive\": true,\n" +
                                    "    \"balance\": \"$2,397.53\",\n" +
                                    "    \"picture\": \"http://placehold.it/32x32\",\n" +
                                    "    \"age\": 30,\n" +
                                    "    \"eyeColor\": \"green\",\n" +
                                    "    \"name\": {\n" +
                                    "      \"first\": \"Marietta\",\n" +
                                    "      \"last\": \"Willis\"\n" +
                                    "    },\n" +
                                    "    \"company\": \"ANACHO\",\n" +
                                    "    \"email\": \"marietta.willis@anacho.ca\",\n" +
                                    "    \"phone\": \"+1 (860) 554-2426\",\n" +
                                    "    \"address\": \"460 Crosby Avenue, Mulberry, Oklahoma, 310\",\n" +
                                    "    \"about\": \"Laboris consectetur exercitation enim veniam nisi commodo proident officia nostrud aliquip esse pariatur nisi duis. Tempor excepteur ullamco fugiat ad labore reprehenderit duis sit velit aliqua elit aute est culpa. Ad tempor commodo elit excepteur nostrud consectetur est officia nostrud. Ex ad culpa elit ex amet aliquip exercitation sunt qui exercitation. Ad exercitation nostrud velit dolor minim deserunt esse et magna voluptate qui duis. Magna et esse qui Lorem enim. Do aute veniam sint adipisicing elit laboris non amet cillum in minim fugiat voluptate.\",\n" +
                                    "    \"registered\": \"Thursday, December 14, 2017 4:30 AM\",\n" +
                                    "    \"latitude\": \"33.199032\",\n" +
                                    "    \"longitude\": \"102.221098\",\n" +
                                    "    \"tags\": [\n" +
                                    "      \"enim\",\n" +
                                    "      \"Lorem\",\n" +
                                    "      \"consequat\",\n" +
                                    "      \"non\",\n" +
                                    "      \"labore\"\n" +
                                    "    ],\n" +
                                    "    \"range\": [\n" +
                                    "      0,\n" +
                                    "      1,\n" +
                                    "      2,\n" +
                                    "      3,\n" +
                                    "      4,\n" +
                                    "      5,\n" +
                                    "      6,\n" +
                                    "      7,\n" +
                                    "      8,\n" +
                                    "      9\n" +
                                    "    ],\n" +
                                    "    \"friends\": [\n" +
                                    "      {\n" +
                                    "        \"id\": 0,\n" +
                                    "        \"name\": \"Knight Bryant\"\n" +
                                    "      },\n" +
                                    "      {\n" +
                                    "        \"id\": 1,\n" +
                                    "        \"name\": \"Maude Kramer\"\n" +
                                    "      },\n" +
                                    "      {\n" +
                                    "        \"id\": 2,\n" +
                                    "        \"name\": \"Eugenia Lynn\"\n" +
                                    "      }\n" +
                                    "    ],\n" +
                                    "    \"greeting\": \"Hello, Marietta! You have 5 unread messages.\",\n" +
                                    "    \"favoriteFruit\": \"strawberry\"\n" +
                                    "  },\n" +
                                    "  {\n" +
                                    "    \"_id\": \"5dcad3973c56998d775db600\",\n" +
                                    "    \"index\": 1,\n" +
                                    "    \"guid\": \"afc97395-47aa-43d4-85eb-8fc03719a792\",\n" +
                                    "    \"isActive\": true,\n" +
                                    "    \"balance\": \"$1,043.83\",\n" +
                                    "    \"picture\": \"http://placehold.it/32x32\",\n" +
                                    "    \"age\": 32,\n" +
                                    "    \"eyeColor\": \"green\",\n" +
                                    "    \"name\": {\n" +
                                    "      \"first\": \"Sonja\",\n" +
                                    "      \"last\": \"Holden\"\n" +
                                    "    },\n" +
                                    "    \"company\": \"DELPHIDE\",\n" +
                                    "    \"email\": \"sonja.holden@delphide.io\",\n" +
                                    "    \"phone\": \"+1 (885) 536-3295\",\n" +
                                    "    \"address\": \"953 Evans Street, Vivian, Minnesota, 4072\",\n" +
                                    "    \"about\": \"Dolor in officia sunt consectetur. Ex laboris ullamco amet ex incididunt culpa id irure. Amet ea duis officia tempor sint commodo id esse nulla reprehenderit.\",\n" +
                                    "    \"registered\": \"Thursday, July 3, 2014 4:20 AM\",\n" +
                                    "    \"latitude\": \"21.730327\",\n" +
                                    "    \"longitude\": \"105.694067\",\n" +
                                    "    \"tags\": [\n" +
                                    "      \"dolor\",\n" +
                                    "      \"ad\",\n" +
                                    "      \"cupidatat\",\n" +
                                    "      \"ad\",\n" +
                                    "      \"ex\"\n" +
                                    "    ],\n" +
                                    "    \"range\": [\n" +
                                    "      0,\n" +
                                    "      1,\n" +
                                    "      2,\n" +
                                    "      3,\n" +
                                    "      4,\n" +
                                    "      5,\n" +
                                    "      6,\n" +
                                    "      7,\n" +
                                    "      8,\n" +
                                    "      9\n" +
                                    "    ],\n" +
                                    "    \"friends\": [\n" +
                                    "      {\n" +
                                    "        \"id\": 0,\n" +
                                    "        \"name\": \"Roxie Watts\"\n" +
                                    "      },\n" +
                                    "      {\n" +
                                    "        \"id\": 1,\n" +
                                    "        \"name\": \"Sally Hardy\"\n" +
                                    "      },\n" +
                                    "      {\n" +
                                    "        \"id\": 2,\n" +
                                    "        \"name\": \"Webb Mullen\"\n" +
                                    "      }\n" +
                                    "    ],\n" +
                                    "    \"greeting\": \"Hello, Sonja! You have 8 unread messages.\",\n" +
                                    "    \"favoriteFruit\": \"banana\"\n" +
                                    "  },\n" +
                                    "  {\n" +
                                    "    \"_id\": \"5dcad397a54aaa6d09e8be2d\",\n" +
                                    "    \"index\": 2,\n" +
                                    "    \"guid\": \"8f6ebc9b-a573-4bc0-aede-9fc5290a70d8\",\n" +
                                    "    \"isActive\": false,\n" +
                                    "    \"balance\": \"$1,678.03\",\n" +
                                    "    \"picture\": \"http://placehold.it/32x32\",\n" +
                                    "    \"age\": 32,\n" +
                                    "    \"eyeColor\": \"green\",\n" +
                                    "    \"name\": {\n" +
                                    "      \"first\": \"Ortiz\",\n" +
                                    "      \"last\": \"Spears\"\n" +
                                    "    },\n" +
                                    "    \"company\": \"COMTRAIL\",\n" +
                                    "    \"email\": \"ortiz.spears@comtrail.me\",\n" +
                                    "    \"phone\": \"+1 (868) 520-3013\",\n" +
                                    "    \"address\": \"312 Knapp Street, Independence, New York, 4547\",\n" +
                                    "    \"about\": \"Qui adipisicing sint cillum tempor excepteur est velit proident magna. Magna dolore aliqua ut laborum elit et labore proident aliqua ad. Aliquip proident ut aliqua officia. Dolore dolor magna cupidatat excepteur nisi dolore incididunt deserunt nulla ut elit. Quis irure qui deserunt nisi laborum aliqua eiusmod dolore sunt do Lorem est quis. Deserunt laborum velit sint non ad amet magna sunt cillum dolore excepteur. Ipsum nulla sunt minim duis incididunt nulla incididunt qui esse commodo velit.\",\n" +
                                    "    \"registered\": \"Wednesday, January 14, 2015 8:15 PM\",\n" +
                                    "    \"latitude\": \"75.396436\",\n" +
                                    "    \"longitude\": \"-63.821966\",\n" +
                                    "    \"tags\": [\n" +
                                    "      \"officia\",\n" +
                                    "      \"non\",\n" +
                                    "      \"officia\",\n" +
                                    "      \"consectetur\",\n" +
                                    "      \"aliqua\"\n" +
                                    "    ],\n" +
                                    "    \"range\": [\n" +
                                    "      0,\n" +
                                    "      1,\n" +
                                    "      2,\n" +
                                    "      3,\n" +
                                    "      4,\n" +
                                    "      5,\n" +
                                    "      6,\n" +
                                    "      7,\n" +
                                    "      8,\n" +
                                    "      9\n" +
                                    "    ],\n" +
                                    "    \"friends\": [\n" +
                                    "      {\n" +
                                    "        \"id\": 0,\n" +
                                    "        \"name\": \"Perez Solis\"\n" +
                                    "      },\n" +
                                    "      {\n" +
                                    "        \"id\": 1,\n" +
                                    "        \"name\": \"Maria Puckett\"\n" +
                                    "      },\n" +
                                    "      {\n" +
                                    "        \"id\": 2,\n" +
                                    "        \"name\": \"Kathie Wagner\"\n" +
                                    "      }\n" +
                                    "    ],\n" +
                                    "    \"greeting\": \"Hello, Ortiz! You have 6 unread messages.\",\n" +
                                    "    \"favoriteFruit\": \"banana\"\n" +
                                    "  },\n" +
                                    "  {\n" +
                                    "    \"_id\": \"5dcad3973381699b88a19e80\",\n" +
                                    "    \"index\": 3,\n" +
                                    "    \"guid\": \"2482985c-8e8a-4afa-b517-d1aa9062b8f3\",\n" +
                                    "    \"isActive\": true,\n" +
                                    "    \"balance\": \"$2,726.03\",\n" +
                                    "    \"picture\": \"http://placehold.it/32x32\",\n" +
                                    "    \"age\": 37,\n" +
                                    "    \"eyeColor\": \"blue\",\n" +
                                    "    \"name\": {\n" +
                                    "      \"first\": \"Mae\",\n" +
                                    "      \"last\": \"Clemons\"\n" +
                                    "    },\n" +
                                    "    \"company\": \"KRAGGLE\",\n" +
                                    "    \"email\": \"mae.clemons@kraggle.co.uk\",\n" +
                                    "    \"phone\": \"+1 (953) 486-3847\",\n" +
                                    "    \"address\": \"987 Dank Court, Hall, North Carolina, 3579\",\n" +
                                    "    \"about\": \"Qui do id laborum minim ex in nisi sint nisi nostrud esse laborum labore ad. Ullamco quis in officia qui officia proident sit laboris nulla consequat. Deserunt voluptate nostrud do sunt qui mollit veniam. Nisi duis id nostrud Lorem duis voluptate cillum cupidatat cillum culpa. Id consequat fugiat velit mollit amet ullamco consectetur consectetur.\",\n" +
                                    "    \"registered\": \"Sunday, October 16, 2016 7:58 PM\",\n" +
                                    "    \"latitude\": \"14.393651\",\n" +
                                    "    \"longitude\": \"-70.664845\",\n" +
                                    "    \"tags\": [\n" +
                                    "      \"voluptate\",\n" +
                                    "      \"adipisicing\",\n" +
                                    "      \"qui\",\n" +
                                    "      \"pariatur\",\n" +
                                    "      \"sint\"\n" +
                                    "    ],\n" +
                                    "    \"range\": [\n" +
                                    "      0,\n" +
                                    "      1,\n" +
                                    "      2,\n" +
                                    "      3,\n" +
                                    "      4,\n" +
                                    "      5,\n" +
                                    "      6,\n" +
                                    "      7,\n" +
                                    "      8,\n" +
                                    "      9\n" +
                                    "    ],\n" +
                                    "    \"friends\": [\n" +
                                    "      {\n" +
                                    "        \"id\": 0,\n" +
                                    "        \"name\": \"Sandra Stein\"\n" +
                                    "      },\n" +
                                    "      {\n" +
                                    "        \"id\": 1,\n" +
                                    "        \"name\": \"Jerry Wise\"\n" +
                                    "      },\n" +
                                    "      {\n" +
                                    "        \"id\": 2,\n" +
                                    "        \"name\": \"Alta Nicholson\"\n" +
                                    "      }\n" +
                                    "    ],\n" +
                                    "    \"greeting\": \"Hello, Mae! You have 6 unread messages.\",\n" +
                                    "    \"favoriteFruit\": \"banana\"\n" +
                                    "  },\n" +
                                    "  {\n" +
                                    "    \"_id\": \"5dcad397b7036b26dbabb623\",\n" +
                                    "    \"index\": 4,\n" +
                                    "    \"guid\": \"cb6de970-2a84-41c1-96ad-9e7523a36430\",\n" +
                                    "    \"isActive\": true,\n" +
                                    "    \"balance\": \"$1,511.21\",\n" +
                                    "    \"picture\": \"http://placehold.it/32x32\",\n" +
                                    "    \"age\": 32,\n" +
                                    "    \"eyeColor\": \"blue\",\n" +
                                    "    \"name\": {\n" +
                                    "      \"first\": \"Cecile\",\n" +
                                    "      \"last\": \"Rodriguez\"\n" +
                                    "    },\n" +
                                    "    \"company\": \"EZENTIA\",\n" +
                                    "    \"email\": \"cecile.rodriguez@ezentia.tv\",\n" +
                                    "    \"phone\": \"+1 (941) 503-2956\",\n" +
                                    "    \"address\": \"114 Hubbard Street, Belmont, Arkansas, 384\",\n" +
                                    "    \"about\": \"Est fugiat consequat commodo proident amet tempor eu reprehenderit. Esse ad et dolor tempor deserunt duis velit est aliquip Lorem sint minim. Esse sunt aute enim anim in dolor fugiat laborum dolore deserunt irure. Qui qui officia exercitation nostrud dolor ut ut officia labore non elit ut. Culpa exercitation et pariatur voluptate laborum in reprehenderit. Sint laborum laboris esse sunt sint in do nulla mollit veniam. Ipsum aliqua ad veniam aliqua adipisicing sunt do.\",\n" +
                                    "    \"registered\": \"Thursday, February 8, 2018 9:17 PM\",\n" +
                                    "    \"latitude\": \"-87.919443\",\n" +
                                    "    \"longitude\": \"110.961205\",\n" +
                                    "    \"tags\": [\n" +
                                    "      \"sunt\",\n" +
                                    "      \"consectetur\",\n" +
                                    "      \"aliquip\",\n" +
                                    "      \"nostrud\",\n" +
                                    "      \"Lorem\"\n" +
                                    "    ],\n" +
                                    "    \"range\": [\n" +
                                    "      0,\n" +
                                    "      1,\n" +
                                    "      2,\n" +
                                    "      3,\n" +
                                    "      4,\n" +
                                    "      5,\n" +
                                    "      6,\n" +
                                    "      7,\n" +
                                    "      8,\n" +
                                    "      9\n" +
                                    "    ],\n" +
                                    "    \"friends\": [\n" +
                                    "      {\n" +
                                    "        \"id\": 0,\n" +
                                    "        \"name\": \"Flora Moreno\"\n" +
                                    "      },\n" +
                                    "      {\n" +
                                    "        \"id\": 1,\n" +
                                    "        \"name\": \"Sheppard Branch\"\n" +
                                    "      },\n" +
                                    "      {\n" +
                                    "        \"id\": 2,\n" +
                                    "        \"name\": \"Farmer Buchanan\"\n" +
                                    "      }\n" +
                                    "    ],\n" +
                                    "    \"greeting\": \"Hello, Cecile! You have 9 unread messages.\",\n" +
                                    "    \"favoriteFruit\": \"banana\"\n" +
                                    "  },\n" +
                                    "  {\n" +
                                    "    \"_id\": \"5dcad397d68b15d55290fe1b\",\n" +
                                    "    \"index\": 5,\n" +
                                    "    \"guid\": \"7a422f0e-aabb-44f4-a8c5-d9eb5994167e\",\n" +
                                    "    \"isActive\": true,\n" +
                                    "    \"balance\": \"$3,317.32\",\n" +
                                    "    \"picture\": \"http://placehold.it/32x32\",\n" +
                                    "    \"age\": 40,\n" +
                                    "    \"eyeColor\": \"brown\",\n" +
                                    "    \"name\": {\n" +
                                    "      \"first\": \"Delores\",\n" +
                                    "      \"last\": \"Ortiz\"\n" +
                                    "    },\n" +
                                    "    \"company\": \"QIAO\",\n" +
                                    "    \"email\": \"delores.ortiz@qiao.net\",\n" +
                                    "    \"phone\": \"+1 (864) 598-3402\",\n" +
                                    "    \"address\": \"415 Ditmars Street, Deltaville, Palau, 4123\",\n" +
                                    "    \"about\": \"Ex adipisicing et do nostrud aliqua sint eiusmod anim elit sunt in esse. Laborum ut nisi et culpa consequat ad excepteur incididunt fugiat id eiusmod eu sit. Magna exercitation pariatur minim minim quis eu amet ex sunt tempor sunt. Consectetur exercitation in aute ad laboris aliquip magna veniam do magna enim est consectetur.\",\n" +
                                    "    \"registered\": \"Friday, December 28, 2018 1:04 AM\",\n" +
                                    "    \"latitude\": \"53.389405\",\n" +
                                    "    \"longitude\": \"-168.761522\",\n" +
                                    "    \"tags\": [\n" +
                                    "      \"exercitation\",\n" +
                                    "      \"dolor\",\n" +
                                    "      \"incididunt\",\n" +
                                    "      \"tempor\",\n" +
                                    "      \"quis\"\n" +
                                    "    ],\n" +
                                    "    \"range\": [\n" +
                                    "      0,\n" +
                                    "      1,\n" +
                                    "      2,\n" +
                                    "      3,\n" +
                                    "      4,\n" +
                                    "      5,\n" +
                                    "      6,\n" +
                                    "      7,\n" +
                                    "      8,\n" +
                                    "      9\n" +
                                    "    ],\n" +
                                    "    \"friends\": [\n" +
                                    "      {\n" +
                                    "        \"id\": 0,\n" +
                                    "        \"name\": \"Holt Hess\"\n" +
                                    "      },\n" +
                                    "      {\n" +
                                    "        \"id\": 1,\n" +
                                    "        \"name\": \"Michelle Pollard\"\n" +
                                    "      },\n" +
                                    "      {\n" +
                                    "        \"id\": 2,\n" +
                                    "        \"name\": \"Shannon Pace\"\n" +
                                    "      }\n" +
                                    "    ],\n" +
                                    "    \"greeting\": \"Hello, Delores! You have 10 unread messages.\",\n" +
                                    "    \"favoriteFruit\": \"banana\"\n" +
                                    "  }\n" +
                                    "]";
}