package pl.poznan.put.cs.scenariochecker.service;

class GetSubScenariosOutputs {

    private GetSubScenariosOutputs() {
    }

    static final String EXPECTED_OUTPUT_LEVEL_ZERO = "{\n" +
            "  \"actors\": [\n" +
            "    \"Actor\"\n" +
            "  ],\n" +
            "  \"systemActor\": \"System Actor\",\n" +
            "  \"title\": \"Title\",\n" +
            "  \"steps\": []\n" +
            "}";

    static final String EXPECTED_OUTPUT_LEVEL_ONE = "{\n" +
            "  \"actors\": [\n" +
            "    \"Actor\"\n" +
            "  ],\n" +
            "  \"systemActor\": \"System Actor\",\n" +
            "  \"title\": \"Title\",\n" +
            "  \"steps\": [\n" +
            "    {\n" +
            "      \"subSteps\": [],\n" +
            "      \"content\": \"IF step\"\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    static final String EXPECTED_OUTPUT_LEVEL_TWO = "{\n" +
            "  \"actors\": [\n" +
            "    \"Actor\"\n" +
            "  ],\n" +
            "  \"systemActor\": \"System Actor\",\n" +
            "  \"title\": \"Title\",\n" +
            "  \"steps\": [\n" +
            "    {\n" +
            "      \"subSteps\": [\n" +
            "        {\n" +
            "          \"subSteps\": [],\n" +
            "          \"content\": \"FOR EACH step\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"subSteps\": [],\n" +
            "          \"content\": \"oneStep\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"subSteps\": [],\n" +
            "          \"content\": \"IF step\"\n" +
            "        }\n" +
            "      ],\n" +
            "      \"content\": \"IF step\"\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    static final String EXPECTED_OUTPUT_LEVEL_THREE = "{\n" +
            "  \"actors\": [\n" +
            "    \"Actor\"\n" +
            "  ],\n" +
            "  \"systemActor\": \"System Actor\",\n" +
            "  \"title\": \"Title\",\n" +
            "  \"steps\": [\n" +
            "    {\n" +
            "      \"subSteps\": [\n" +
            "        {\n" +
            "          \"subSteps\": [\n" +
            "            {\n" +
            "              \"subSteps\": [],\n" +
            "              \"content\": \"oneStep\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"subSteps\": [],\n" +
            "              \"content\": \"IF step\"\n" +
            "            }\n" +
            "          ],\n" +
            "          \"content\": \"FOR EACH step\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"subSteps\": [],\n" +
            "          \"content\": \"oneStep\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"subSteps\": [\n" +
            "            {\n" +
            "              \"subSteps\": [],\n" +
            "              \"content\": \"FOR EACH step\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"subSteps\": [],\n" +
            "              \"content\": \"oneStep\"\n" +
            "            }\n" +
            "          ],\n" +
            "          \"content\": \"IF step\"\n" +
            "        }\n" +
            "      ],\n" +
            "      \"content\": \"IF step\"\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    static final String EXPECTED_OUTPUT_LEVEL_ONE_HUNDRED = "{\n" +
            "  \"actors\": [\n" +
            "    \"Actor\"\n" +
            "  ],\n" +
            "  \"systemActor\": \"System Actor\",\n" +
            "  \"title\": \"Title\",\n" +
            "  \"steps\": [\n" +
            "    {\n" +
            "      \"subSteps\": [\n" +
            "        {\n" +
            "          \"subSteps\": [\n" +
            "            {\n" +
            "              \"subSteps\": [],\n" +
            "              \"content\": \"oneStep\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"subSteps\": [\n" +
            "                {\n" +
            "                  \"subSteps\": [],\n" +
            "                  \"content\": \"oneStep\"\n" +
            "                }\n" +
            "              ],\n" +
            "              \"content\": \"IF step\"\n" +
            "            }\n" +
            "          ],\n" +
            "          \"content\": \"FOR EACH step\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"subSteps\": [],\n" +
            "          \"content\": \"oneStep\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"subSteps\": [\n" +
            "            {\n" +
            "              \"subSteps\": [\n" +
            "                {\n" +
            "                  \"subSteps\": [],\n" +
            "                  \"content\": \"oneStep\"\n" +
            "                },\n" +
            "                {\n" +
            "                  \"subSteps\": [\n" +
            "                    {\n" +
            "                      \"subSteps\": [],\n" +
            "                      \"content\": \"oneStep\"\n" +
            "                    }\n" +
            "                  ],\n" +
            "                  \"content\": \"IF step\"\n" +
            "                }\n" +
            "              ],\n" +
            "              \"content\": \"FOR EACH step\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"subSteps\": [],\n" +
            "              \"content\": \"oneStep\"\n" +
            "            }\n" +
            "          ],\n" +
            "          \"content\": \"IF step\"\n" +
            "        }\n" +
            "      ],\n" +
            "      \"content\": \"IF step\"\n" +
            "    }\n" +
            "  ]\n" +
            "}";
}