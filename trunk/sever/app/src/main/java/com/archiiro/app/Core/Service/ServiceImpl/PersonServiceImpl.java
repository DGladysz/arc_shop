package com.archiiro.app.Core.Service.ServiceImpl;

import com.archiiro.app.Core.Domain.*;
import com.archiiro.app.Core.Dto.PersonAddressDto;
import com.archiiro.app.Core.Dto.PersonDto;
import com.archiiro.app.Core.Other.Constants;
import com.archiiro.app.Core.Repository.*;
import com.archiiro.app.Core.Service.FileDescriptionService;
import com.archiiro.app.Core.Service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Service
public class PersonServiceImpl extends SupportServiceImpl<Person, Long> implements PersonService {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private FileDescriptionService fileDescriptionService;
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private AdministrativeUnitRepository administrativeUnitRepository;
    @Autowired
    private EthnicsRepository ethnicsRepository;
    @Autowired
    private ReligionRepository religionRepository;
    @Autowired
    private PersonAddressRepository personAddressRepository;

    private static final Logger logger = LoggerFactory.getLogger(PersonServiceImpl.class);

    @Override
    public List<PersonDto> getAll() {
        return this.personRepository.getAll();
    }

    @Override
    public PersonDto getPersonDto(Long id) {
        if(id != null) {
            Person person =  this.findOne(id);
            if(person != null) {
                return new PersonDto(person);
            }
        }
        return null;
    }

    @Override
    public PersonDto savePerson(PersonDto dto, Long id) {
        if(dto == null) {
            return null;
        }
        Person person = null;
        LocalDateTime currentDate = LocalDateTime.now();
        String currentUser = "Unknow";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null) {
            currentUser = authentication.getName();
        }
        boolean isNew = false;
        if(id != null) {
            person = this.findOne(id);
            person.setModifiedBy(currentUser);
            person.setModifyDate(currentDate);
        }
        if(person == null && dto.getId() != null) {
            person = this.findOne(dto.getId());
            person.setModifiedBy(currentUser);
            person.setModifyDate(currentDate);
        }
        if(person == null) {
            person = new Person();
            isNew = true;
            person.setCreateBy(currentUser);
            person.setCreateDate(currentDate);
            person.setModifiedBy(currentUser);
            person.setModifyDate(currentDate);
        }
        if(dto.getLastName() != null) {
            person.setFirstName(dto.getFirstName());
        }
        if(dto.getLastName() != null) {
            person.setLastName(dto.getLastName());
        }
        if(dto.getDisplayName() != null) {
            person.setDisplayName(dto.getDisplayName());
        } else if(dto.getFirstName() != null || dto.getLastName() != null) {
            person.setDisplayName(dto.getFirstName() + " " + dto.getLastName());
        }
        if(dto.getBirthDate() != null) {
            person.setBirthDate(dto.getBirthDate());
        }
        if(dto.getBirthPlace() != null) {
            person.setBirthPlace(dto.getBirthPlace());
        }
        if(dto.getGender() != null) {
            person.setGender(dto.getGender());
        }
        if(dto.getEmail() != null) {
            person.setEmail(dto.getEmail());
        }
        if(dto.getPhoneNumber() != null) {
            person.setPhoneNumber(dto.getPhoneNumber());
        }
        if(dto.getIdNumber() != null) {
            person.setIdNumber(dto.getIdNumber());
        }
        if(dto.getIdNumberIssueBy() != null) {
            person.setIdNumberIssueBy(dto.getIdNumberIssueBy());
        }
        if(dto.getIdNumberIssueDate() != null) {
            person.setIdNumberIssueDate(dto.getIdNumberIssueDate());
        }
        if(dto.getNationality() != null && dto.getNationality().getId() != null) {
            Country country = countryRepository.getById(dto.getNationality().getId());
            if(country != null) {
                person.setNationality(country);
            }
        }
        if(dto.getEthnics() != null && dto.getEthnics().getId() != null) {
            Ethnics ethnics = ethnicsRepository.getById(dto.getEthnics().getId());
            if(ethnics != null) {
                person.setEthnics(ethnics);
            }
        }
        if(dto.getReligion() != null && dto.getReligion().getId() != null) {
            Religion religion = religionRepository.getById(dto.getReligion().getId());
            if(religion != null) {
                person.setReligion(religion);
            }
        }
        if(dto.getAddress() != null && dto.getAddress().size() > 0) {
            Iterator<PersonAddressDto> iterator = dto.getAddress().iterator();
            HashSet<PersonAddress> personAddresses = new HashSet<PersonAddress>();
            while (iterator.hasNext()) {
                PersonAddressDto personAddressDto = iterator.next();
                PersonAddress personAddress = null;
                if(personAddressDto.getId() != null) {
                    personAddress = personAddressRepository.getById(personAddressDto.getId());
                }
                if(personAddress == null) {
                    personAddress = new PersonAddress();
                }
                if(personAddressDto.getAddress() != null) {
                    personAddress.setAddress(personAddressDto.getAddress());
                }
                if(personAddressDto.getCity() != null) {
                    personAddress.setCity(personAddressDto.getCity());
                }
                if(personAddressDto.getProvince() != null) {
                    personAddress.setProvince(personAddressDto.getProvince());
                }
                if(personAddressDto.getCountry() != null) {
                    personAddress.setCountry(personAddressDto.getCountry());
                }
                personAddresses.add(personAddress);
            }
            if(person.getAddress() != null) {
                person.getAddress().clear();
                person.getAddress().addAll(personAddresses);
            } else {
                person.setAddress(personAddresses);
            }
        }
        person = this.save(person);
        return new PersonDto(person);
    }
    @Override
    public PersonDto saveImage(Long id, MultipartFile multipartFile) {
        if(id != null) {
            try {
                String extension = multipartFile.getOriginalFilename().split("\\.(?=[^\\.]+$)")[1];
                UUID randomCode = UUID.randomUUID();
                String fileName = randomCode + "." + extension;
                try {
                    File fileToBeSaved = new File(Constants.LOCAL_PATH, fileName);
                    multipartFile.transferTo(fileToBeSaved);
                } catch (Exception e) {
                    logger.error("Error: ", e.getMessage(), e);
                }

                FileDescription file = new FileDescription();
                file.setContentSize(multipartFile.getSize());
                file.setContentType(multipartFile.getContentType());
                file.setName(fileName);
                Person person = null;
                person = this.findOne(id);
                if(person != null) {
                    file = fileDescriptionService.save(file);
                    person.setImagePath(file.getName());
                    person = this.save(person);
                    return new PersonDto(person);
                }
            } catch (Exception e) {
                logger.error("Error upload image: " + e.getMessage(), e);
                return null;
            }
        }
        return null;
    }

    @Override
    public Boolean deletePerson(Long id) {
        if(id != null) {
            if(this.findOne(id) != null) {
                this.delete(id);
                return true;
            }
        }
        return false;
    }

    @Override
    public Page<Person> getListPage(int pageIndex, int pageSize) {
        return this.getPage(pageIndex, pageSize);
    }
}
