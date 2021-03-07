package ru.dosport.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.dosport.enums.SurfaceType;

/**
 * Маппер, преобразующий классы SurfaceType и String друг в друга
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SurfaceTypeMapper {

    default String mapEnumToString(SurfaceType entity) {
        return entity.getDescription();
    }

    default SurfaceType mapStringToEnum(String string) {
        SurfaceType entity;
        switch (string) {
            case "Не выбран":
            case "":
                entity = SurfaceType.NOT_SELECTED;
                break;
            case "Грунт":
                entity = SurfaceType.SOIL;
                break;
            case "Песок":
                entity = SurfaceType.SAND;
                break;
            case "Асфальт":
                entity = SurfaceType.ASPHALT;
                break;
            case "Резина":
                entity = SurfaceType.RUBBER;
                break;
            case "Искуственный газон":
                entity = SurfaceType.ARTIFICIAL_LAWN;
                break;
            case "Натуральный газон":
                entity = SurfaceType.NATURAL_LAWN;
                break;
            case "Паркет":
                entity = SurfaceType.PARQUET;
                break;
            case "Лед":
                entity = SurfaceType.ICE;
                break;
            case "Хард":
                entity = SurfaceType.HARD;
                break;
            case "Маты":
                entity = SurfaceType.MATS;
                break;
            case "Бассейн":
                entity = SurfaceType.POOL;
                break;
            default:
                entity = SurfaceType.NOT_SELECTED;
                break;
        }
        return entity;
    }
}
