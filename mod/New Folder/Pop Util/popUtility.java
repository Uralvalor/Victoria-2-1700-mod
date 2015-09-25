    import java.io.*;
    import java.awt.*;
    import java.awt.event.*;
    import javax.swing.*;
    import java.util.*;
    
    public class popUtility extends JFrame implements ActionListener {
        //variables
        String inputlocation, outputlocation;
        Label input;
        Label output;
        JButton browseInput;
        JButton browseOutput;
        JButton selectAll;
        JButton deselect;
        JButton compile;
        Label poptypes[];
        TextField popsizes[];
        JCheckBox popfiles[];
        JFileChooser filechooser, filechooser1;
        Font small;
        Font medium;
        String tab = "\t";
        TextArea text;
        
        JScrollPane checkboxes;
        JPanel checkboxes1;
        int z;
        public popUtility(String title) throws IOException {
        super(title);
        
        small = new Font("Times", Font.PLAIN, 10);
        medium = new Font("Times",Font.PLAIN,12);
        
        //variable initialization
        compile = new JButton("Compile");
        filechooser = new JFileChooser();
        filechooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        filechooser1 = new JFileChooser();
        filechooser1.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        browseInput = new JButton("Browse..");
        browseOutput = new JButton("Browse..");
        input = new Label("Input Directory");
        output = new Label("Output Directory");
        checkboxes = new JScrollPane();
        checkboxes1 = new JPanel();
        selectAll = new JButton("Select All");
        deselect = new JButton("Clear");
        text = new TextArea("",0,20,3);
        
        //beginning functions
        poptypesInit();
        
        //component initialization
        add(compile);
        add(browseInput);
        add(browseOutput);
        add(input);
        add(output);
        add(checkboxes);
        add(text);
        for(z=0;z<poptypes.length;z++){
            add(poptypes[z]);
            add(popsizes[z]);
        }
        
        //action listeners
        compile.setActionCommand("compile");
        compile.addActionListener(this);
        browseInput.setActionCommand("browseInput");
        browseInput.addActionListener(this);
        browseOutput.setActionCommand("browseOutput");
        browseOutput.addActionListener(this);
        selectAll.setActionCommand("selectAll");
        selectAll.addActionListener(this);
        deselect.setActionCommand("deselect");
        deselect.addActionListener(this);
        for(z=0;z<popsizes.length;z++){
            popsizes[z].addActionListener(this);
        }
        
        //sizes and locations and layouts
        setLayout(new BorderLayout());
        compile.setSize(75,25);
        compile.setLocation(350,525);
        compile.setFont(small);
        browseInput.setSize(75,25);
        browseInput.setLocation(130,50);
        browseInput.setFont(small);
        browseOutput.setLocation(330,50);
        browseOutput.setSize(75,25);
        browseOutput.setFont(small);
        input.setSize(100,25);
        output.setSize(100,25);
        input.setLocation(25,50);
        output.setLocation(225,50);
        checkboxes.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        checkboxes1.setLayout(new BoxLayout(checkboxes1, BoxLayout.PAGE_AXIS));
        for(z=0;z<poptypes.length;z++){
            poptypes[z].setFont(medium);
            poptypes[z].setSize(90,25);
            popsizes[z].setSize(50,25);
            poptypes[z].setLocation(200,100+37*z);
            popsizes[z].setLocation(295,100+37*z);
        }
        text.setLocation(175,0);
        text.setSize(100,25);
        text.setEditable(false);
        //default
        setResizable(false);
        setSize(450, 600);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void poptypesInit() {
        poptypes = new Label[12];
        popsizes = new TextField[12];
        poptypes[0] = new Label("aristocrats(%)");
        popsizes[0] = new TextField("100");
        poptypes[1] = new Label("artisans(%)");
        popsizes[1] = new TextField("100");
        poptypes[2] = new Label("bureaucrats(%)");
        popsizes[2] = new TextField("100");
        poptypes[3] = new Label("capitalists(%)");
        popsizes[3] = new TextField("100");
        poptypes[4] = new Label("clergymen(%)");
        popsizes[4] = new TextField("100");
        poptypes[5] = new Label("clerks(%)");
        popsizes[5] = new TextField("100");
        poptypes[6] = new Label("craftsmen(%)");
        popsizes[6] = new TextField("100");
        poptypes[7] = new Label("farmers(%)");
        popsizes[7] = new TextField("100");
        poptypes[8] = new Label("labourers(%)");
        popsizes[8] = new TextField("100");
        poptypes[9] = new Label("officers(%)");
        popsizes[9] = new TextField("100");
        poptypes[10] = new Label("slaves(%)");
        popsizes[10] = new TextField("100");
        poptypes[11] = new Label("soldiers(%)");
        popsizes[11] = new TextField("100");
        
    }
    
    public void inputFiles()throws IOException {
        File actual = new File(inputlocation);
        z=0;
        for( File f : actual.listFiles()){
            z = z+1;
        }
        
        Scanner input[] = new Scanner[z];
        popfiles = new JCheckBox[z];
        z=0;
        selectAll.setFont(small);
        deselect.setFont(small);
        checkboxes1.add(selectAll);
        checkboxes1.add(deselect);
        
        for( File f : actual.listFiles()){
            input[z] = new Scanner(new File(inputlocation+ "/"+f.getName())); 
            popfiles[z] = new JCheckBox((f.getName().toString().replace(".txt","")));
            popfiles[z].setFont(small);
            checkboxes1.add(popfiles[z]);
            
            z = z+1;
        }
        checkboxes = new JScrollPane(checkboxes1);
        add(checkboxes);
        checkboxes.setVisible(true);
        checkboxes.setSize(150,400);
        checkboxes.setLocation(35,100);
        checkboxes.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
   }
   
    public void Compile() throws IOException {
        File actual = new File(inputlocation);
        Scanner inputfiles[];
        Scanner inputtext;
        PrintWriter outputfiles[];
        PrintWriter outputtext;
        String filenames[] = new String[popfiles.length];
        int x=0;
        int y = 0;
        z=0;
        for( File f : actual.listFiles()){
            
            filenames[z] = new String(f.getName());
            z=z+1;
        }
        x=0;
        for(z=0;z<popfiles.length;z++){
            if(popfiles[z].isSelected()==true){
                x=x+1;
            }
        }
        inputfiles = new Scanner[x];
        x=0;
        for(z=0;z<popfiles.length;z++){
           if(popfiles[z].isSelected()==true){
               
               for( File f : actual.listFiles()){
                   
                   if(f.getName().toString().toUpperCase().equals(filenames[z].toUpperCase())){
                       inputfiles[y] = new Scanner(new File(inputlocation+"/"+f.getName()));
                       y=y+1;
                       
                    }
                   
               }
               x=x+1;
            }
           
        }
        outputfiles = new PrintWriter[x];
        x=0;
        for(z=0;z<popfiles.length;z++){
           if(popfiles[z].isSelected()==true){
               outputfiles[x] = new PrintWriter(outputlocation+"/"+filenames[z]);
               x=x+1;
               
            }
           
        }
        boolean stop = false;
        x=0;
        for(z=0;z<outputfiles.length;z++){
            while(inputfiles[z].hasNext()){
                
                while(!inputfiles[z].hasNextInt()){
                    if(!inputfiles[z].hasNextLine()){
                        stop = true;
                        break;
                        
                    }
                    inputfiles[z].nextLine();
                    
                }
                if(stop == true){
                    stop = false;
                    break;
                    
                }
                
                String province = inputfiles[z].next();
                outputfiles[z].println(province+" = {");
                inputfiles[z].next();
                inputfiles[z].next();
                while(!inputfiles[z].hasNext("}")){
                    
                    String type = inputfiles[z].next();
                    if(type.startsWith("#")){
                        inputfiles[z].nextLine();
                        type = inputfiles[z].next();
                    }
                    outputfiles[z].println(tab+type+" = {");
                    inputfiles[z].next();
                    inputfiles[z].next();
                    inputfiles[z].next();
                    inputfiles[z].next();
                    String culture = inputfiles[z].next();
                    outputfiles[z].println(tab+tab+"culture = "+culture);
                    String turkish = inputfiles[z].next();
                    if(turkish.startsWith("#")){
                        inputfiles[z].next();
                    }
                    inputfiles[z].next();
                    String religion = inputfiles[z].next();
                    outputfiles[z].println(tab+tab+"religion = "+religion);
                     inputfiles[z].next();
                    inputfiles[z].next();
                    boolean xyz = false;
                    while(!inputfiles[z].hasNextInt()){
                        String funx = inputfiles[z].next();
                        if(funx.startsWith("#")){
                            outputfiles[z].println(tab+tab+"size = 10000");
                            outputfiles[z].println(tab+"}");
                            xyz = true;
                            break;
                        }
                    }
                    if(xyz == true){
                        xyz = false;
                        break;
                        
                    }
                    int size = Integer.parseInt(inputfiles[z].next());
                    
                    if(type.equalsIgnoreCase("aristocrats")){
                        int multiply = Integer.parseInt(popsizes[0].getText().toString());
                    outputfiles[z].println(tab+tab+"size = "+(size*multiply)/100);
                    
                    }
                    if(type.equalsIgnoreCase("artisans")){
                        int multiply = Integer.parseInt(popsizes[1].getText().toString());
                    outputfiles[z].println(tab+tab+"size = "+(size*multiply)/100);
                    
                    }
                    if(type.equalsIgnoreCase("bureaucrats")){
                        int multiply = Integer.parseInt(popsizes[2].getText().toString());
                    outputfiles[z].println(tab+tab+"size = "+(size*multiply)/100);
                    
                    }
                    if(type.equalsIgnoreCase("capitalists")){
                        int multiply = Integer.parseInt(popsizes[3].getText().toString());
                    outputfiles[z].println(tab+tab+"size = "+(size*multiply)/100);
                    
                    }
                    if(type.equalsIgnoreCase("clergymen")){
                        int multiply = Integer.parseInt(popsizes[4].getText().toString());
                    outputfiles[z].println(tab+tab+"size = "+(size*multiply)/100);
                    
                    }
                    if(type.equalsIgnoreCase("clerks")){
                        int multiply = Integer.parseInt(popsizes[5].getText().toString());
                    outputfiles[z].println(tab+tab+"size = "+(size*multiply)/100);
                    
                    }
                    if(type.equalsIgnoreCase("craftsmen")){
                        int multiply = Integer.parseInt(popsizes[6].getText().toString());
                    outputfiles[z].println(tab+tab+"size = "+(size*multiply)/100);
                    
                    }
                    if(type.equalsIgnoreCase("farmers")){
                        int multiply = Integer.parseInt(popsizes[7].getText().toString());
                    outputfiles[z].println(tab+tab+"size = "+(size*multiply)/100);
                    
                    }
                    if(type.equalsIgnoreCase("labourers")){
                        int multiply = Integer.parseInt(popsizes[8].getText().toString());
                    outputfiles[z].println(tab+tab+"size = "+(size*multiply)/100);
                    
                    }
                    if(type.equalsIgnoreCase("officers")){
                        int multiply = Integer.parseInt(popsizes[9].getText().toString());
                    outputfiles[z].println(tab+tab+"size = "+(size*multiply)/100);
                    
                    }
                    if(type.equalsIgnoreCase("slaves")){
                        int multiply = Integer.parseInt(popsizes[10].getText().toString());
                    outputfiles[z].println(tab+tab+"size = "+(size*multiply)/100);
                    
                    }
                    if(type.equalsIgnoreCase("soldiers")){
                        int multiply = Integer.parseInt(popsizes[11].getText().toString());
                    outputfiles[z].println(tab+tab+"size = "+(size*multiply)/100);
                    
                    }
                    if(inputfiles[z].hasNext("militancy")){
                        inputfiles[z].next();
                        inputfiles[z].next();
                        String militancy = inputfiles[z].next();
                        outputfiles[z].println(tab+tab+"militancy = "+militancy);
                    }
                    if(inputfiles[z].hasNext("rebel_type")){
                        inputfiles[z].next();
                        inputfiles[z].next();
                        String rebelType = inputfiles[z].next();
                        outputfiles[z].println(tab+tab+"rebel_type = "+rebelType);
                    }
                    outputfiles[z].println(tab+"}");
                    turkish = inputfiles[z].next();
                    if(turkish.startsWith("#")){
                        inputfiles[z].nextLine();
                    }
                    
                    }
                   outputfiles[z].println("}");
                   
            
        }
        
        outputfiles[z].close();
    }
}
    public void actionPerformed(ActionEvent evt) {
        if(evt.getActionCommand()=="browseInput"){
        int returnVal = filechooser.showOpenDialog(this);
        boolean stop = false;
            if(returnVal == JFileChooser.APPROVE_OPTION){
                
                File file = filechooser.getSelectedFile();
                inputlocation = file.getAbsolutePath();
            
            }
            if(returnVal==JFileChooser.CANCEL_OPTION){
                stop = true;
            }
            if(stop == false){
            try{
            inputFiles();
        } catch(IOException e){System.out.println("n");}
    }
        }
        if(evt.getActionCommand()=="browseOutput"){
        int returnVal = filechooser1.showOpenDialog(this);
            if(returnVal == JFileChooser.APPROVE_OPTION){
                File file = filechooser1.getSelectedFile();
                outputlocation = file.getAbsolutePath();
                
            }
            
        }
        if(evt.getActionCommand()=="selectAll"){
            for(int x = 0;x<popfiles.length;x++){
                popfiles[x].setSelected(true);
            }
            
        }
        if(evt.getActionCommand()=="deselect"){
            for(int x = 0;x<popfiles.length;x++){
                popfiles[x].setSelected(false);
            }
        }
        if(evt.getActionCommand()=="compile"){
            if(!outputlocation.equals(null)){
            try{
                Compile();
            } catch(IOException e){ System.out.println("file exception");}
          }else{ 
              text.replaceRange("select output location",0,20);
            }
        }
        repaint();
    }
    
    public static void main(String[] args) throws IOException {
        popUtility pops = new popUtility("Population Utility");

}
}