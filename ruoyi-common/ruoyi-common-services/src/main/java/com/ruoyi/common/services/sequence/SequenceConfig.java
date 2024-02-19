package com.ruoyi.common.services.sequence;

import com.ruoyi.common.core.utils.StringUtils;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 序列号生成器配置
 *
 * @author Alan Scipio
 * created on 2024/2/19
 */
@Getter
@Component
public class SequenceConfig {

    private final Map<String, ISequenceGenerator> generatorMap = new HashMap<>();

    public SequenceConfig() {
        registerGenerator(new DefaultSequenceGenerator());
    }

    public void registerGenerator(ISequenceGenerator generator) {
        if (generator == null) {
            throw new IllegalArgumentException("generator can not be null");
        }
        generatorMap.put(generator.getClass().getName(), generator);
    }

    public ISequenceGenerator getGenerator(String generatorName) {
        return generatorMap.get(generatorName);
    }

    public ISequenceGenerator getOrCreateGenerator(String generatorName) {
        if (StringUtils.isBlank(generatorName)) {
            throw new IllegalArgumentException("generatorName can not be blank");
        }
        return generatorMap.computeIfAbsent(generatorName, name -> {
            try {
                ISequenceGenerator generator = (ISequenceGenerator) Class.forName(name).getDeclaredConstructor().newInstance();
                registerGenerator(generator);
                return generator;
            } catch (Exception e) {
                throw new IllegalArgumentException("generatorName: [" + name + "] can not be instantiated", e);
            }
        });
    }

    public ISequenceGenerator getDefaultGenerator() {
        return generatorMap.get(DefaultSequenceGenerator.class.getName());
    }

}
