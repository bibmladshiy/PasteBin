package com.example.pastebin.controllerPasta;

import com.example.pastebin.dtoPasta.*;
import com.example.pastebin.exception.CustomNoSuchPasteException;
import com.example.pastebin.servicePasta.PastaServiceImp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(value = "/PasteBin")
public class PastaController {
    private PastaServiceImp pastaServImp;
    @Autowired
    public PastaController(PastaServiceImp pastaServImp) {
        this.pastaServImp = pastaServImp;
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.FOUND)
    public List<String> findTenPastas() {
        return pastaServImp.findTenPastas();
    }

    @PostMapping("/create-pasta")
    @ResponseStatus(HttpStatus.CREATED)
    public String  createPasta(@Validated @RequestBody PastaCreationDto pastaCreationDto) {
        return pastaServImp.createPasta(pastaCreationDto);
    }

    @DeleteMapping("/delete-pasta")
    @ResponseStatus(HttpStatus.GONE)
    public void deletePasta(@Validated @RequestBody PastaDeleteDto pastaDeleteDto) {
        pastaServImp.deletePasta(pastaDeleteDto.getPastaName());

    }

    @PutMapping("/update-pasta-name")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updatePastaName(@Validated @RequestBody PastaUpdateNameDto pastaUpdateNameDto) {
        pastaServImp.updatePastaName(pastaUpdateNameDto.getPastaNameNew(), pastaUpdateNameDto.getPastaName());
    }

    @GetMapping("/{pasta-url}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String findByUrl (@PathVariable("pasta-url") String url) throws CustomNoSuchPasteException {
        return pastaServImp.findByUrl("http://localhost:8081/PasteBin/"+url);
    }
}
