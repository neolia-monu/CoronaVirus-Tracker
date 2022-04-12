package com.example.coronavirustracker.Controller;


import com.example.coronavirustracker.Model.LocationStats;
import com.example.coronavirustracker.Services.CoronaVirusDeathData;
import com.example.coronavirustracker.Services.CoronaVirusServiceData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    CoronaVirusServiceData coronaVirusServiceData;
    @Autowired
    CoronaVirusDeathData coronaVirusDeathData;

    @GetMapping("/")
    public String home(Model model){
        List<LocationStats> allStats = coronaVirusServiceData.getAllStats();
        int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
        int totalNewCases = allStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();

        model.addAttribute("locationStats", allStats);
        model.addAttribute("totalReportedCases", totalReportedCases);
        model.addAttribute("totalNewCases", totalNewCases);
        return "home";
    }

    @GetMapping("/death")
    public String death(Model model){
        List<LocationStats> allStats = coronaVirusDeathData.getAllStats();
        int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
        int totalNewCases = allStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();

        model.addAttribute("locationStats", allStats);
        model.addAttribute("totalReportedCases", totalReportedCases);
        model.addAttribute("totalNewCases", totalNewCases);
        return "death";
    }
}
