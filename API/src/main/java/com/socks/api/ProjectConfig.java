//*** в этом файле (интерфейс) подсоеденяемся к файлу, где описаны наши проперти. Может быть локально, может удаленно

package com.socks.api;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:config.properties"})  //туту задается путь к файлу где находятся наши проперти.  classpath: - значит корневая папка src/...
public interface ProjectConfig extends Config {


    @DefaultValue("dev")  // этим куском кода мы выбераем какой энваермент (env = dev) запустить и какое значение (host = htp://)
    String env();

    @Key("${env}.host")



    String host();

}