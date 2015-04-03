/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.home;

import fr.home.entity.Club;
import fr.home.entity.Department;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Thibaut
 */
public class ChessMailScript {

    public static final String URL_CHESS_ROOT = "http://echecs.asso.fr";
    public static final String URL_CHESS_CLUBS = URL_CHESS_ROOT + "/ListeClubs.aspx?Action=CLUBCOMITE&ComiteRef=";

    public static final String TAG_TABLE_PAGE = "TablePage";
    public static final String TAG_LIST_LINK = "lien_liste";

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        List<Department> departments = new ArrayList<>();
        departments.add(getDepartmentWithClub(67, "Bas-Rhin"));
        departments.add(getDepartmentWithClub(68, "Haut-Rhin"));
        departments.add(getDepartmentWithClub(88, "Vosges"));
        departments.add(getDepartmentWithClub(90, "Territoire de Belfort"));
        departments.add(getDepartmentWithClub(54, "Meurthe-et-Moselle"));
        departments.add(getDepartmentWithClub(70, "Haute-Saône"));

        for (Department department : departments) {
            for (Club c : department.getClubs()) {
                System.out.println(c.getName());
                for (String mail_leader : c.getLeaders()) {
                    System.out.println(mail_leader);
                }
            }
        }

    }

    public static Department getDepartmentWithClub(int departmentNumber, String departmentName) throws IOException {
        Department newDepartment = new Department(departmentNumber, departmentName);
        Document doc = Jsoup.connect(URL_CHESS_CLUBS + departmentNumber).get();

        Element content = doc.getElementById(TAG_TABLE_PAGE);
        Elements ref_clubs = content.getElementsByClass(TAG_LIST_LINK);

        List<String> clubs = new ArrayList<>();
        ref_clubs.stream().forEach((club) -> {
            clubs.add(club.attr("href"));
        });

        for (String club : clubs) {
            Club c = new Club();
            Document sheet = Jsoup.connect(URL_CHESS_ROOT + "/" + club).get();
            Element name = sheet.getElementById("ctl00_ContentPlaceHolderMain_LinkInfo");
            c.setName(name.text());
            Element sheet_content = sheet.getElementById("ctl00_ContentPlaceHolderMain_TableClub");
            Elements sheet_mails = sheet_content.getElementsByClass("lien_texte");

            sheet_mails.stream()
                    .map((sheet_mail) -> sheet_mail.attr("href"))
                    .filter((mail_str) -> (mail_str.startsWith("mailto")))
                    .forEach((mail_str) -> {
                        c.getLeaders().add(mail_str.substring(7));
                    });

            c.setRef(sheet_content.getElementById("ctl00_ContentPlaceHolderMain_LinkJoueurs").attr("href"));
            Document players_sheet = Jsoup.connect(URL_CHESS_ROOT + "/" + c.getRef()).get();
            Elements players_mails = players_sheet.getElementsByClass("lien_texte");

            players_mails.stream()
                    .map((sheet_mail) -> sheet_mail.attr("href"))
                    .filter((mail_str) -> (mail_str.startsWith("mailto")))
                    .forEach((mail_str) -> {
                        c.getPlayers().add(mail_str.substring(7));
                    });
            newDepartment.getClubs().add(c);
        }
        return newDepartment;
    }
}
