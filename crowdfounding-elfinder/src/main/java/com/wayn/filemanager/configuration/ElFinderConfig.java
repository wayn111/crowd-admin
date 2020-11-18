package com.wayn.filemanager.configuration;

import com.wayn.filemanager.command.CommandFactory;
import com.wayn.filemanager.constant.ElFinderConstants;
import com.wayn.filemanager.core.Volume;
import com.wayn.filemanager.core.VolumeSecurity;
import com.wayn.filemanager.core.impl.DefaultVolumeSecurity;
import com.wayn.filemanager.core.impl.SecurityConstraint;
import com.wayn.filemanager.param.Node;
import com.wayn.filemanager.service.ElfinderStorage;
import com.wayn.filemanager.service.ElfinderStorageFactory;
import com.wayn.filemanager.service.VolumeSources;
import com.wayn.filemanager.service.impl.DefaultElfinderStorage;
import com.wayn.filemanager.service.impl.DefaultElfinderStorageFactory;
import com.wayn.filemanager.service.impl.DefaultThumbnailWidth;
import com.wayn.filemanager.support.locale.LocaleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;

@Configuration
public class ElFinderConfig {

    @Autowired
    private ElfinderConfiguration elfinderConfiguration;

    @Bean(name = "commandFactory")
    public CommandFactory getCommandFactory() {
        CommandFactory commandFactory = new CommandFactory();
        commandFactory.setClassNamePattern("com.wayn.filemanager.command.%sCommand");
        return commandFactory;
    }


    @Bean(name = "elfinderStorageFactory")
    public ElfinderStorageFactory getElfinderStorageFactory() {
        DefaultElfinderStorageFactory elfinderStorageFactory = new DefaultElfinderStorageFactory();
        elfinderStorageFactory.setElfinderStorage(getElfinderStorage());
        return elfinderStorageFactory;
    }

    @Bean(name = "elfinderStorage")
    public ElfinderStorage getElfinderStorage() {

        DefaultElfinderStorage defaultElfinderStorage = new DefaultElfinderStorage();

        // creates thumbnail
        DefaultThumbnailWidth defaultThumbnailWidth = new DefaultThumbnailWidth();
        defaultThumbnailWidth.setThumbnailWidth(elfinderConfiguration.getThumbnail().getWidth().intValue());

        // creates volumes, volumeIds, volumeLocale and volumeSecurities
        Character defaultVolumeId = 'A';
        List<Node> elfinderConfigurationVolumes = Arrays.asList(elfinderConfiguration.getVolume());
        List<Volume> elfinderVolumes = new ArrayList<>(elfinderConfigurationVolumes.size());
        Map<Volume, String> elfinderVolumeIds = new HashMap<>(elfinderConfigurationVolumes.size());
        Map<Volume, Locale> elfinderVolumeLocales = new HashMap<>(elfinderConfigurationVolumes.size());
        List<VolumeSecurity> elfinderVolumeSecurities = new ArrayList<>();

        // creates volumes
        for (Node elfinderConfigurationVolume : elfinderConfigurationVolumes) {

            final String alias = elfinderConfigurationVolume.getAlias();
            final String path = elfinderConfigurationVolume.getPath();
            final String source = elfinderConfigurationVolume.getSource();
            final String locale = elfinderConfigurationVolume.getLocale();
            final boolean isLocked = elfinderConfigurationVolume.getConstraint().isLocked();
            final boolean isReadable = elfinderConfigurationVolume.getConstraint().isReadable();
            final boolean isWritable = elfinderConfigurationVolume.getConstraint().isWritable();

            // creates new volume
            Volume volume = VolumeSources.of(source).newInstance(alias, path);

            elfinderVolumes.add(volume);
            elfinderVolumeIds.put(volume, Character.toString(defaultVolumeId));
            elfinderVolumeLocales.put(volume, LocaleUtils.toLocale(locale));

            // creates security constraint
            SecurityConstraint securityConstraint = new SecurityConstraint();
            securityConstraint.setLocked(isLocked);
            securityConstraint.setReadable(isReadable);
            securityConstraint.setWritable(isWritable);

            // creates volume pattern and volume security
            final String volumePattern = Character.toString(defaultVolumeId) + ElFinderConstants.ELFINDER_VOLUME_SERCURITY_REGEX;
            elfinderVolumeSecurities.add(new DefaultVolumeSecurity(volumePattern, securityConstraint));

            // prepare next volumeId character
            defaultVolumeId++;
        }

        defaultElfinderStorage.setThumbnailWidth(defaultThumbnailWidth);
        defaultElfinderStorage.setVolumes(elfinderVolumes);
        defaultElfinderStorage.setVolumeIds(elfinderVolumeIds);
        defaultElfinderStorage.setVolumeLocales(elfinderVolumeLocales);
        defaultElfinderStorage.setVolumeSecurities(elfinderVolumeSecurities);

        return defaultElfinderStorage;
    }

}
