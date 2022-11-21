package com.archiiro.app.Core.Service.ServiceImpl;

import com.archiiro.app.Core.Domain.FileDescription;
import com.archiiro.app.Core.Domain.Person;
import com.archiiro.app.Core.Dto.PersonDto;
import com.archiiro.app.Core.Other.Constants;
import com.archiiro.app.Core.Repository.PersonRepository;
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
import java.util.List;
import java.util.UUID;

@Service
public class PersonServiceImpl extends SupportServiceImpl<Person, Long> implements PersonService {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private FileDescriptionService fileDescriptionService;

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
        // image
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
