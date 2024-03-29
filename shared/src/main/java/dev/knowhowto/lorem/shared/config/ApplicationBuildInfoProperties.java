package dev.knowhowto.lorem.shared.config;

import jakarta.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("lorem.build")
public record ApplicationBuildInfoProperties(@NotNull String version,
                                             @NotNull String description,
                                             @NotNull String relativePath) {

  public String getVersion() {
    return version();
  }

  public String getDescription() {
    return description();
  }

  public String getRelativePath() {
    return relativePath();
  }
}
