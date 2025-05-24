package com.pdsc.ashpath.domain.service;

import com.pdsc.ashpath.domain.dto.request.CreateDeceasedRequest;
import com.pdsc.ashpath.domain.dto.response.DeceasedResponse;
import com.pdsc.ashpath.domain.entity.Deceased;
import com.pdsc.ashpath.repository.DeceasedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DeceasedService {

    private final DeceasedRepository deceasedRepository;


    public void createDeceased(CreateDeceasedRequest request, MultipartFile certificate) throws IOException {

        Deceased deceased = new Deceased();

        deceased.setFullname(request.getFullname());
        deceased.setBirthDate(request.getBirthDate());
        deceased.setDeathDate(request.getDeathDate());
        deceased.setCauseOfDeath(request.getCauseOfDeath());
        deceased.setFatherName(request.getFatherName());
        deceased.setMotherName(request.getMotherName());
        deceased.setDeathCertificate(certificate.getBytes());

        deceasedRepository.save(deceased);
    }

    public DeceasedResponse readDeceasedById(Long deceasedId){

        Optional<Deceased> optionalDeceased = deceasedRepository.findById(deceasedId);

        if (optionalDeceased.isPresent())
        {
            Deceased deceased = optionalDeceased.get();

            String server = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();

            DeceasedResponse deceasedResponse = new DeceasedResponse(deceased);
            deceasedResponse.setDeathCertificateDownloadLink(server +"/deceased/"+ deceased.getId() +"/deathCertificate");

            return deceasedResponse;
        }
        return null;
    }

    public List<Deceased> findAllByCremationEntryId(Long cremationId){
        return deceasedRepository.findAllDeceasedByQueueId(cremationId);
    }

    public Deceased findById(Long id ){
        return deceasedRepository.findById(id).orElse(null);
    }

}
