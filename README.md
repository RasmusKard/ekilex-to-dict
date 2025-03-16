# ekilex-to-dict

Maps JSON data onto classes from [this](https://github.com/keeleinstituut/ekilex/commit/a0f1be5353335fd90926bf03f08b1c5e97396212) keeleinstituut/ekilex commit

## Notable compromises done for agile development

1. The word forms from the list of paradigms are currently grouped into one. (Not sure if this is an issue)
1. Deserialization currently not possible due to `Article` using derived property for serialization
