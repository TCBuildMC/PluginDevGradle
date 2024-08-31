package xyz.tcbuildmc.minecraft.plugin.gradle.metadata.generate

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper

import java.util.function.Function

class YamlGenerator implements IGenerator {
    static final YamlGenerator INSTANCE = new YamlGenerator()

    private final YAMLMapper mapper = YAMLMapper.builder()
            .disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER)
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .enable(YAMLGenerator.Feature.MINIMIZE_QUOTES)
            .enable(YAMLGenerator.Feature.ALWAYS_QUOTE_NUMBERS_AS_STRINGS)
            .enable(YAMLGenerator.Feature.INDENT_ARRAYS_WITH_INDICATOR)
            .build()
    private YamlGenerator() {}

    @Override
    Function<Map<String, Object>, String> getGenerator() {
        return (map) -> this.mapper.writer().withDefaultPrettyPrinter()::writeValueAsString
    }
}
