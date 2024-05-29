package com.minsih.chronoman.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.minsih.chronoman.model.Site;
import com.minsih.chronoman.service.SiteService;

@RestController
@RequestMapping("/api/sites")
public class SiteController {

    private final SiteService siteService;

    public SiteController(SiteService siteService) {
        this.siteService = siteService;
    }

    @GetMapping
    public ResponseEntity<List<Site>> getAllSites() {
        List<Site> sites = siteService.findAll();
        return new ResponseEntity<>(sites, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Site> getSiteById(@PathVariable Long id) {
        Site site = siteService.findById(id);
        return site != null ? new ResponseEntity<>(site, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Site> createSite(@RequestBody Site site) {
        Site createdSite = siteService.save(site);
        return new ResponseEntity<>(createdSite, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Site> updateSite(@PathVariable Long id, @RequestBody Site site) {
        Site existingSite = siteService.findById(id);
        if (existingSite != null) {
            site.setId(id);
            Site updatedSite = siteService.save(site);
            return new ResponseEntity<>(updatedSite, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSite(@PathVariable Long id) {
        Site site = siteService.findById(id);
        if (site != null) {
            siteService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
