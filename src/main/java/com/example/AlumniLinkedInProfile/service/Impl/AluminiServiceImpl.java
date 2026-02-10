package com.example.AlumniLinkedInProfile.service.Impl;

import com.example.AlumniLinkedInProfile.DTO.RequestDTO;
import com.example.AlumniLinkedInProfile.client.PhantomBusterClient;
import com.example.AlumniLinkedInProfile.entity.AlumniProfile;
import com.example.AlumniLinkedInProfile.repository.AlumniRepository;
import com.example.AlumniLinkedInProfile.service.AlumniService;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
@Service
public class AluminiServiceImpl implements AlumniService {

    private final AlumniRepository alumniRepository;
    private final PhantomBusterClient phantomBusterClient;

    public AluminiServiceImpl(
            AlumniRepository alumniRepository,
            PhantomBusterClient phantomBusterClient
    ) {
        this.alumniRepository = alumniRepository;
        this.phantomBusterClient = phantomBusterClient;
    }

    @Override
    public List<AlumniProfile> searchAll(RequestDTO dto) {

        List<AlumniProfile> result;

        // 1️⃣ Try DB
        if (dto.getPassOutYear() != null) {
            result = alumniRepository
                    .findByUniversityAndDesignationAndPassOutYear(
                            dto.getUniversity(),
                            dto.getDesignation(),
                            dto.getPassOutYear()
                    );
        } else {
            result = alumniRepository.findAllByUniversityAndDesignation(
                            dto.getUniversity(),
                            dto.getDesignation()
                    );
        }

        // 2️⃣ DB hit
        if (!result.isEmpty()) {
            return result;
        }

        // 3️⃣ DB miss → PhantomBuster
        String searchUrl = buildLinkedInSearchUrl(dto);

        String phantomResponse =
                phantomBusterClient.launchLinkedInSearch(searchUrl);

        // 4️⃣ Parse + save
        List<AlumniProfile> fetchedProfiles =
                parsePhantomResponse(phantomResponse, dto);

        alumniRepository.saveAll(fetchedProfiles);

        return fetchedProfiles;
    }

    private String buildLinkedInSearchUrl(RequestDTO dto) {

        String base =
                "https://www.linkedin.com/search/results/people/?keywords=";

        String keywords = dto.getUniversity() + " " + dto.getDesignation();

        if (dto.getPassOutYear() != null) {
            keywords += " " + dto.getPassOutYear();
        }

        return base + URLEncoder.encode(
                keywords, StandardCharsets.UTF_8
        );
    }

    private List<AlumniProfile> parsePhantomResponse(
            String response, RequestDTO dto) {

        AlumniProfile profile = new AlumniProfile();
        profile.setUniversity(dto.getUniversity());
        profile.setDesignation(dto.getDesignation());
        profile.setPassOutYear(dto.getPassOutYear());
        profile.setName("Fetched from LinkedIn");

        return List.of(profile);
    }
}

