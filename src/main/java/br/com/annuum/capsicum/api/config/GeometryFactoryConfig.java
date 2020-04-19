package br.com.annuum.capsicum.api.config;

import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.PrecisionModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeometryFactoryConfig {

    //Spatial Resource Identifier (SRID)
    //The value 4326 indicates the World Geodetic System of 1984 (WGS 84)
    private static final int SRID = 4326;

    @Bean
    public GeometryFactory geometryFactory() {
        return new GeometryFactory(new PrecisionModel(), SRID);
    }
}