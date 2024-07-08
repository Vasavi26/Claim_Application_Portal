package com.claimapplicationportal.user.pdfconfiguration;
import com.claimapplicationportal.user.model.Claim;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.extern.slf4j.Slf4j;
import java.io.FileOutputStream;

@Slf4j
public class PdfGenerator {

    public String writeIntoFile(Claim claim){
        String path="C:\\Users\\2135400\\OneDrive - Cognizant\\Documents\\Claim Portal Frontend\\ClaimPortalFrontend\\src\\assets\\ClaimDetails.pdf";
        try{


            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(path));  // loading the existing file
            document.open();
            Font font = FontFactory.getFont(FontFactory.COURIER, 20, BaseColor.BLUE);
            Font georgia = FontFactory.getFont("georgia", 12f);

            Chunk heading=new Chunk("Claim Details",font);
            Chunk chunk = new Chunk("\nClaim ID : "+claim.getClaimId(),georgia);
            Chunk chunk1 = new Chunk("\nFull Name : "+claim.getFullName(),georgia);
            Chunk chunk2 = new Chunk("\nEmail : "+claim.getEmail(),georgia);
            Chunk chunk3 = new Chunk("\nAge : "+claim.getAge(),georgia);
            Chunk chunk4 = new Chunk("\nServices : "+claim.getServices(),georgia);
            Chunk chunk5 = new Chunk("\nHospital Id : "+claim.getHospitalId(),georgia);
            Chunk chunk6 = new Chunk("\nAddress : "+claim.getAddress(),georgia);
            Chunk chunk7 = new Chunk("\nTemplate Id : "+claim.getTemplateId(),georgia);
            Chunk chunk8 = new Chunk("\nCity : "+claim.getCityName(),georgia);
            Chunk chunk9 = new Chunk("\nState : "+claim.getStateName(),georgia);
            Chunk chunk10 = new Chunk("\nClaim Amount : "+claim.getClaimAmount(),georgia);
            Chunk chunk11 = new Chunk("\nGender: "+claim.getGender(),georgia);

            Phrase phrase=new Phrase();
            phrase.add(heading);
            phrase.add(chunk1);
            phrase.add(chunk);                                                     //phrase-new line
            phrase.add(chunk2);
            phrase.add(chunk3);
            phrase.add(chunk4);
            phrase.add(chunk5);
            phrase.add(chunk6);
            phrase.add(chunk7);
            phrase.add(chunk8);
            phrase.add(chunk9);
            phrase.add(chunk10);
            phrase.add(chunk11);

            Paragraph paragraph=new Paragraph();                              //entire phrase in paragraph
            paragraph.add(phrase);

            document.add(paragraph);
            document.close();
        }catch(Exception e){
            log.info(e.toString());
        }
        return path;
    }


}
